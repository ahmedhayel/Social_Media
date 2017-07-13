package org.social.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.social.DTO.UserDTO;
import org.social.entities.User;
import org.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController
@MultipartConfig
@CrossOrigin
public class SignInController {

	@Autowired
	private UserService userService;

	@Value("${folder-user_image}")
	private String path;

	@RequestMapping(method = RequestMethod.POST, value = "/signIn_SignUp", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO signIn_SignUp(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter resultPrinter = response.getWriter();

		Gson gson = new Gson();
		JsonObject responseObject = new JsonObject();

		if (request.getParameter("action").equals("signin")) {

			String email = request.getParameter("email");
			String pass = request.getParameter("password");

			User user = new User();
			try {
				user = userService.getUserByEmailAndPassword(email, pass);
			} catch (Exception e) {
				user = null;
			}
			UserDTO userDTO = new UserDTO(user.getIdUser(), user.getUsername(),user.getPicture_URL(),user.getFirstName(),user.getLastName(),user.getAdresse());
			JsonElement userJSONObj = gson.toJsonTree(userDTO);

			if (userDTO == null) {
				responseObject.addProperty("success", false);
			} else {
				responseObject.addProperty("success", true);
			}

			responseObject.add("user", userJSONObj);

			resultPrinter.println(responseObject.toString());

			resultPrinter.close();

		} else {
			if (request.getParameter("action").equals("signup")) {
				boolean isMultipart;
				String filePath;
				int maxFileSize = 50 * 1024;
				int maxMemSize = 4 * 1024;
				File file;

				response.setContentType("text/html;charset=UTF-8");

				System.out.println("enter in servelt mapping");

				final String username = request.getParameter("username");
				final String email = request.getParameter("email");

				// password encoding

				final String password = request.getParameter("password");
				final String firstName = request.getParameter("first");
				final String lastName = request.getParameter("last");
				final String adress = request.getParameter("adress");
				final Part filePart = request.getPart("photo");
				String fileName = null;

				final String partHeader = filePart.getHeader("content-disposition");

				for (String content : partHeader.split(";")) {
					if (content.trim().startsWith("filename")) {
						fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
					}
				}

				String[] content = new String[2];
				content = fileName.split("[.]");
				String extension = content[1];

				OutputStream out = null;
				InputStream filecontent = null;
				final PrintWriter writer = response.getWriter();

				try {
					path = path + username + "Profile." + extension;
					String databasePath = "userImages/" + username + "Profile." + extension;
					out = new FileOutputStream(new File(path));

					filecontent = filePart.getInputStream();

					int read = 0;
					final byte[] bytes = new byte[1024];

					while ((read = filecontent.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					writer.println("upload complet");
					User user = userService.addUser(
							new User(username, email, password, databasePath, false, firstName, lastName, adress));
					//return new UserDTO(user.getIdUser(), user.getUsername(),user.getPicture_URL(),user.getFirstName(),user.getLastName(),user.getAdresse());

				} catch (FileNotFoundException fne) {
					writer.println("You either did not specify a file to upload or are "
							+ "trying to upload a file to a protected or nonexistent " + "location.");
					writer.println("<br/> ERROR: " + fne.getMessage());

				} finally {
					if (out != null) {
						out.close();
					}
					if (filecontent != null) {
						filecontent.close();
					}
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		return null;
	}

}
