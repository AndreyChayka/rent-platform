package com.rent.controllers.ads;

import com.rent.models.Item;
import com.rent.models.User;
import com.rent.repository.ItemRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/main")
    public String getMain(Model model, Principal principal){
        User user = userRepository.findByLogin(principal.getName());
        Iterable<Item> items = itemRepository.findAll();

//        System.out.println(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return "main";
    }
}
