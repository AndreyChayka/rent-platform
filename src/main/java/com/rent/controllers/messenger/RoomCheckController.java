package com.rent.controllers.messenger;

import com.rent.models.ChatRoom;
import com.rent.models.User;
import com.rent.repository.ChatRoomRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class RoomCheckController {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/message_room_check/{ownerId}")
    public String check(@PathVariable(value = "ownerId") long ownerId, Principal principal){
        User user = userRepository.findByLogin(principal.getName());
        ChatRoom chatRoom = checkRoom(ownerId, user.getId());
        if (chatRoom == null){
            long roomId = createChatRoom(ownerId, user.getId());
            return "redirect:/message/" + roomId;
        }
        else {
            return "redirect:/message/" + chatRoom.getId();
        }

    }

    private ChatRoom checkRoom(long user1, long user2){
        ChatRoom chat1 = chatRoomRepository.findByUser1AndUser2(user1, user2);
        ChatRoom chat2 = chatRoomRepository.findByUser1AndUser2(user2, user2);
        if (chat1 != null){return chat1;}
        if (chat2 != null){return chat2;}
        else{return null;}
    }

    private long createChatRoom(long user1, long user2){
        ChatRoom chatRoom = new ChatRoom(user1, user2);
        chatRoomRepository.save(chatRoom);
        return chatRoom.getId();
    }
}
