package com.rent.controllers.ads;

import com.rent.models.Item;
import com.rent.repository.ItemRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class DeleteAdController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable(value = "id") long itemId) throws IOException {
        Item item = itemRepository.getById(itemId);
        long userId = item.getOwner();
        removeImage(item.getImagePath());
        itemRepository.deleteById(itemId);
        return "redirect:/rents/" + userId;
    }

    private void removeImage(String dbPath) throws IOException {
        String path = "/Users/andrey/IdeaProjects/rent_mvn/src/main/resources/static" + dbPath;
        path = path.replaceAll(" ", "");
        Files.delete(Path.of(path));
    }
}
