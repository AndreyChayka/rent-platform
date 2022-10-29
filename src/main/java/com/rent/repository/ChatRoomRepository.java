package com.rent.repository;

import com.rent.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByUser1(long usr1);
    List<ChatRoom> findByUser2(long usr2);

    ChatRoom findByUser1AndUser2(long user1, long user2);

    @Query(value = "select u.id, u.first_name, u.second_name, u.image_path, r.id as room_id from usr u, chat_room r " +
            "where u.id=r.user2 and r.user1=:usr", nativeQuery = true)
    List<String> findUser2ByUser1(@Param("usr") long usr);

    @Query(value = "select u.id, u.first_name, u.second_name, u.image_path, r.id as room_id from usr u, chat_room r " +
            "where u.id=r.user1 and r.user2=:usr", nativeQuery = true)
    List<String> findUser1ByUser2(@Param("usr") long usr);
}
