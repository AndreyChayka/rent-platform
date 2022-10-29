package com.rent.assistants;

import com.rent.models.Message;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MessageUser extends Message {
    private String firstName;
    private String secondName;
    private String image_path;

    public String getFirstName() {return firstName;}

    public String getSecondName() {return secondName;}

    public String getImage_path() {return image_path;}

    public MessageUser(String params){
        String[] param = params.split(",");
        this.setText(param[3]);
        this.setDate(LocalDateTime.parse(param[4].replace(" ", "T")));
        this.firstName = param[5];
        this.secondName = param[6];
        this.image_path = param[7];
    }
}
