package org.social.repository;

import java.util.List;

import org.social.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select user from #{#entityName} user where user.idUser IN :ids ")
	public List<User> findUsersByIds(@Param("ids") List<Long> ids);

	public User findUserByEmail(String email);

	@Query("select user from #{#entityName} user where (user.username like %:string% or user.firstName like %:string% or user.lastName like %:string% or user.adresse like %:string%) and user.idUser!=:id ")
	public List<User> findUserByUsernameContaining(@Param("string") String string, @Param("id") long id);

	public User findUserByEmailAndPassword(String email, String password);

	@Query("select f from #{#entityName} user INNER JOIN user.friends f where user.idUser=:id")
	public List<User> getUserFriends(@Param("id") long id);

	public Long countByUsernameAndEmailAndPassword(String username, String email, String password);

	@Query(value = "select * from users where user_id=?1", nativeQuery = true)
	public User findUserById(long id);

	@Query(value = "select * from users LIMIT 50", nativeQuery = true)
	public User findAlluser();

}
