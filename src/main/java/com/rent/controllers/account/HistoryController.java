package com.rent.controllers.account;

import com.rent.assistants.ItemRent;
import com.rent.models.User;
import com.rent.repository.RentRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    RentRepository rentRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/history/{id}")
    public String historyPage(Model model, @PathVariable(value = "id") long userId){
        User user = userRepository.getById(userId);
        List<String> rentsOwner = rentRepository.findRentItemOwner(userId);
        List<ItemRent> ownerRents = new ArrayList<>();
        for (String param: rentsOwner){
            ownerRents.add(new ItemRent(param));
        }

//        System.out.println(rentsOwner);
//        OwnerRentHistory rentOwner = OwnerRentHistory(rentsOwner);
        model.addAttribute("user", user);
        model.addAttribute("rents", ownerRents);
        return "history";
    }

}
