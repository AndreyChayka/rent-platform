package com.rent.controllers.ads;


import com.rent.models.Rent;
import com.rent.models.User;
import com.rent.repository.RentRepository;
import com.rent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class DealController {
    @Autowired
    RentRepository rentRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/deal/{id}")
    public String getDealPage(Model model){
        return "deal";
    }

    @PostMapping("/deal/{id}")
    public String deal(@RequestParam(name = "calendar1") String date1,
                     @RequestParam(name = "calendar2") String date2,
                     @PathVariable(value = "id") Long itemId,
                     Principal principal, Model model){
        LocalDate date_1 = LocalDate.parse(date1);
        LocalDate date_2 = LocalDate.parse(date2);
       if (checkDates(itemId, date_1, date_2)){
           User user = userRepository.findByLogin(principal.getName());
           Rent rent = new Rent(itemId, user.getId(), date_1, date_2);
           rentRepository.save(rent);
           return "redirect:/main/";
       }
       else{
            model.addAttribute("Error", "Бронь на выбранные даты невозможна");
            return "deal";
       }
    }

    private boolean checkDates(long itemId, LocalDate date_1, LocalDate date_2){
        List<Rent> rentStartDate = rentRepository.findByItemAndStartDateBetween(itemId, date_1, date_2);
        List<Rent> rentEndDate = rentRepository.findByItemAndEndDateBetween(itemId, date_1, date_2);

        if (rentStartDate.size() == 0 && rentEndDate.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }


}
