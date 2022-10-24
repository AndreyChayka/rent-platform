package com.rent.controllers.account;

import com.rent.models.Item;
import com.rent.models.User;
import com.rent.repository.ItemRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyRentController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/rents/{id}")
    public String rents(Model model, @PathVariable(value = "id") long userId){
        Iterable<Item> items = itemRepository.findByOwner(userId);
        User user = userRepository.getById(userId);
        model.addAttribute("items", items);
        model.addAttribute("user", user);
        return "myRents";
    }

}
