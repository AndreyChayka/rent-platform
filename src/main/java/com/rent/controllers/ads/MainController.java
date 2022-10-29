package com.rent.controllers.ads;

import com.rent.models.Item;
import com.rent.models.User;
import com.rent.repository.ItemRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

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

        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return "main";
    }

    @PostMapping("/main")
    public String searchAds(@RequestParam("search") String searchWord, RedirectAttributes redirectAttributes){
        List<Item> searchedAds = search(searchWord);
        redirectAttributes.addAttribute("items", searchedAds);
        return "redirect:/search";
    }

    private List<Item> search(String searchWord){
        List<Item> itemsTitle = itemRepository.findByTitleContaining(searchWord);
        List<Item> itemsAbout = itemRepository.findByAboutContaining(searchWord);
        List<Item> result = unique(itemsTitle, itemsAbout);
        return result;
    }

    private List<Item> unique(List<Item> itemsTitle, List<Item> itemsAbout){
        for(Item about: itemsAbout){
            if(!itemsTitle.contains(about)){
                itemsTitle.add(about);
            }
        }
        return itemsTitle;
    }

}
