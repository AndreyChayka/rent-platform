package com.rent.controllers.account;

import com.rent.models.User;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class ChangeUserDataController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/change/{id}")
    public String changeUserData(@PathVariable(value = "id") long userId, Model model){
        User user = userRepository.getById(userId);
        model.addAttribute("user", user);
        return "changeUserData";
    }

    @PostMapping("/change/{id}")
    public String saveChanges(@PathVariable(value = "id") long userId,
                              @RequestParam("file") MultipartFile file,
                              User userData){
        User user = userRepository.getById(userId);
        if (!file.isEmpty()) {
            try {
                uploadFile(file, userId);
                user.setImagePath("/avatars/" + userId + ".jpg");
            } catch (Exception e) {
                return "Вам не удалось загрузить фото" + e.getMessage();
            }
        }
        if (userData.getFirstName()!=null){
            user.setFirstName(userData.getFirstName());
        }
        if(userData.getSecondName()!=null){
            user.setSecondName(userData.getSecondName());
        }
        userRepository.save(user);
        return "redirect:/main";
    }

    private void uploadFile(MultipartFile file, long userId) throws IOException {
        String imagePath = "/Users/andrey/IdeaProjects/rent_mvn/src/main/resources/static/avatars/" + userId + ".jpg";

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imagePath)));
        stream.write(bytes);
        stream.close();
    }
}
