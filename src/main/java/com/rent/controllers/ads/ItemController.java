package com.rent.controllers.ads;


import com.rent.models.Item;
import com.rent.models.User;
import com.rent.repository.ItemRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/item/{id}")
    public String itemList(Model model, @PathVariable(value = "id") long itemID, Principal principal){
        User user = userRepository.findByLogin(principal.getName());
        Item item = itemRepository.getById(itemID);
        User owner = userRepository.getById(item.getOwner());
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        model.addAttribute("owner", owner);
        return "item";
    }
}
