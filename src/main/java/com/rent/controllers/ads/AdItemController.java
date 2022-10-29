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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class AdItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/add/{id}")
    public String addItemPage(Model model, @PathVariable(value = "id") long userId){
        User user = userRepository.getById(userId);
        model.addAttribute("user", user);
        return "addItem";
    }

    @PostMapping("/add/{id}")
    public String addItem(@PathVariable(value = "id") long userId,
                          @RequestParam("file") MultipartFile file,
                          Item item){
        if (!file.isEmpty()) {
            try {
                uploadFile(file, userId);
                item.setImagePath(" /images/" + userId + ".jpg");
            } catch (Exception e) {
                return "Вам не удалось загрузить фото" + e.getMessage();
            }
        }
        item.setOwner(userId);
        itemRepository.save(item);
        return "redirect:/page/" + userId;
    }

    private void uploadFile(MultipartFile file, long userId) throws IOException {
        String imagePath = "/Users/andrey/IdeaProjects/rent_mvn/src/main/resources/static/images/" + userId + ".jpg";

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(imagePath)));
        stream.write(bytes);
        stream.close();
    }
}
