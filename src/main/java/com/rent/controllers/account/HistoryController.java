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

        List<ItemRent> ownerRents = getRentsOwner(userId);
        List<ItemRent> renterRents = getRentsRenter(userId);

        model.addAttribute("user", user);
        model.addAttribute("rentsOwn", ownerRents);
        model.addAttribute("renterRents", renterRents);
        return "history";
    }

    private List<ItemRent> getRentsOwner(long userId){
        List<String> rents = rentRepository.findRentItemOwner(userId);
        List<ItemRent> ownerRents = new ArrayList<>();
        for (String param: rents){
            ownerRents.add(new ItemRent(param));
        }
        return ownerRents;
    }

    private List<ItemRent> getRentsRenter(long userId){
        List<String> rents = rentRepository.findRentItemRenter(userId);
        List<ItemRent> renterRents = new ArrayList<>();
        for (String param: rents){
            renterRents.add(new ItemRent(param));
        }
        return renterRents;
    }

}
