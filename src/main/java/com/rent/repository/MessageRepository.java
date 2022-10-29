package com.rent.repository;

import com.rent.assistants.MessageUser;
import com.rent.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRoomIdOrderByDate(long roomId);

    @Query(value = "select m.*, u.first_name, u.second_name, u.image_path from message m, usr u where m.room_id=:roomId " +
            "and m.sender=u.id order by date", nativeQuery = true)
    List<String> findMessageData(@Param("roomId") long roomId);
}
