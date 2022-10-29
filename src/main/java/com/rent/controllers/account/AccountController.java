package com.rent.controllers.account;

import com.rent.models.User;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/page/{id}")
    public String userPage(Model model, @PathVariable(value = "id") long userID){
        User user = userRepository.getById(userID);
        model.addAttribute("user", user);
        return "account";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(Model model, @PathVariable(value = "id") long userID){
        User user = userRepository.getById(userID);
        model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long userID) throws IOException {
        User user = userRepository.getById(userID);
        if (user.getImagePath() != null){
            removeImage(user.getImagePath());
        }
        userRepository.deleteById(userID);
        return "redirect:/";
    }

    private void removeImage(String dbPath) throws IOException {
        String path = "/Users/andrey/IdeaProjects/rent_mvn/src/main/resources/static" + dbPath;
        path = path.replaceAll(" ", "");
        Files.delete(Path.of(path));
    }
}
