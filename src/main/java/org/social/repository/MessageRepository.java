package org.social.repository;

import java.util.List;

import org.social.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query(value = "SELECT * FROM MESSAGE WHERE (user_receiver=?1 and user_transmitter=?2) or (user_receiver=?2 and user_transmitter=?1)  ORDER BY date desc LIMIT 15", nativeQuery = true)
	public List<Message> findMessageTop50ByIdUserAndIdFriendOrderByDateDesc(long userId, long friendId);

	@Query(value = "SELECT * FROM media.message where date in ( select max(date) FROM media.message group by user_receiver) and user_receiver=?1 and user_transmitter=?2 order by user_receiver", nativeQuery = true)
	public Message getLastMessageNotification(long userId, long idFriend);

	@Query(value = "SELECT * FROM media.message where date in ( select max(date) FROM media.message group by user_receiver) and user_receiver=?1 order by user_receiver", nativeQuery = true)
	public List<Message> getAllMessageNotification(long userId);

	@Query(value = "SELECT count(*) FROM MESSAGE WHERE REEDED=0 AND USER_RECEIVER=?1 AND USER_TRANSMITTER=?2", nativeQuery = true)
	public int countNotifications(long userId, long idFriend);

	@Modifying
	@Transactional
	@Query("update Message m set m.status=true where m.idUser=:userId and m.idUser=:idFriend")
	public List<Message> readedMessage(@Param("userId") long userId, @Param("userId") long idFriend);

}
