package com.rent.controllers.ads;

import com.rent.models.Item;
import com.rent.models.User;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchResultController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/search")
    public String getItems(@RequestParam("items") List<Item> items, Model model, Principal principal){
        User user = userRepository.findByLogin(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return "searchingResult";
    }
}
