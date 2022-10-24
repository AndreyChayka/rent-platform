package com.rent.controllers.messenger;

import com.rent.assistants.RoomUser;
import com.rent.models.ChatRoom;
import com.rent.models.User;
import com.rent.repository.ChatRoomRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatRoomsController {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/messenger/{id}")
    public String getMassageRoom(Model model, @PathVariable(value = "id") long usrId){
        User user = userRepository.getById(usrId);
        List<RoomUser> users = chatRoomsData(usrId);
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        return "messenger";
    }

    private List<RoomUser> chatRoomsData(long usrId){
        List<RoomUser> users = getUser2ByUser1(usrId);
        users.addAll(getUser1ByUser2(usrId));
        return users;
    }

    private List<RoomUser> getUser2ByUser1(long usrId){
        List<String> userData = chatRoomRepository.findUser2ByUser1(usrId);
        List<RoomUser> usr2 = new ArrayList<>();
        for (String data: userData) {
            usr2.add(new RoomUser(data));
        }
        return usr2;
    }

    private List<RoomUser> getUser1ByUser2(long usrId){
        List<String> userData = chatRoomRepository.findUser1ByUser2(usrId);
        List<RoomUser> usr1 = new ArrayList<>();
        for (String data: userData) {
            usr1.add(new RoomUser(data));
        }
        return usr1;
    }

}
