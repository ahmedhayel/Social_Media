package org.social.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.social.DTO.InvitationMinify;
import org.social.DTO.MessageReceiver;
import org.social.DTO.ResultMessage;
import org.social.DTO.StandardSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ServerEndpoint("/severChat")
public class MessageSocket {

	public static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		Gson standard = new Gson();
		StandardSocket socket = null;
		String username = (String) session.getUserProperties().get("username");
		if (message.indexOf("_authentication:") == 0) {
			if (username == null) {
				session.getUserProperties().put("username", message.substring(16));
				session.getBasicRemote().sendText(message.substring(16) + " you are connecting now...");
			}
		} else {
			socket = standard.fromJson(message, StandardSocket.class);
		}
		if (socket != null) {
			switch (socket.getType()) {
			case "message":
				MessageReceiver p = null;
				Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				p = g.fromJson(socket.getMessage(), MessageReceiver.class);
				if (p != null) {

					for (Session client : clients) {
						if (!client.equals(session) && p.getTo().equals(client.getUserProperties().get("username"))) {
							client.getBasicRemote().sendText(g.toJson(
									new ResultMessage(p.getMessage().getMessageContent(), p.getMessage().getDate())));
						}
					}
				}
				break;
			case "invitation":
				for (Session client : clients) {
					if (!client.equals(session) && socket.getTo().equals(client.getUserProperties().get("username"))) {
						InvitationMinify minify = standard.fromJson(socket.getMessage(), InvitationMinify.class);

						client.getBasicRemote().sendText(standard.toJson(minify));
					}
				}
				break;
			}
		}

	}

	@OnOpen
	public void onOpen(Session session) {
		// Add session to the connected sessions set
		clients.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		// Remove session from the connected sessions set
		clients.remove(session);
	}

}
