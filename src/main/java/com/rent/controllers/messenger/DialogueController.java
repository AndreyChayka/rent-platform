package com.rent.controllers.messenger;

import com.rent.assistants.MessageUser;
import com.rent.models.Message;
import com.rent.models.User;
import com.rent.repository.MessageRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DialogueController {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/message/{id}")
    public String getDialogue(@PathVariable(value = "id") long roomId, Model model){
        List<MessageUser> messages = getMessages(roomId);
        model.addAttribute("messages", messages);
        return "dialogue";
    }

    @PostMapping("/message/{id}")
    public String sendMessage(@RequestParam("text") String text,
                              Principal principal,
                              @PathVariable(value = "id") long roomId){
        User user = userRepository.findByLogin(principal.getName());
        saveMessage(user.getId(), roomId, text);
        return "redirect:/message/" + roomId;
    }


    private List<MessageUser> getMessages(long roomId){
        List<String> messageData = messageRepository.findMessageData(roomId);
        List<MessageUser> messages = new ArrayList<>();
        for(String param: messageData){
            messages.add(new MessageUser(param));
        }
        return messages;
    }

    private void saveMessage(long senderId, long roomId,  String text){
        Message message = new Message();
        message.setSender(senderId);
        message.setText(text);
        message.setRoomId(roomId);
        message.setDate(LocalDateTime.now());
        messageRepository.save(message);
    }
}
