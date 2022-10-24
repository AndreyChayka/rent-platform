package com.rent.controllers.ads;


import com.rent.models.Item;
import com.rent.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public String rootPage(Model model){
        Iterable<Item> items = itemRepository.findAll();
//        System.out.println("========================================================================================");
//        for (Item item: items
//             ) {
//            System.out.println(item.getId());
//        }
//        System.out.println("========================================================================================");
        model.addAttribute("items", items);
        return "root";
    }
}
