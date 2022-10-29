package com.rent.assistants;

public class RoomUser {
    private long userId;
    private String firstName;
    private String secondName;
    private String image_path;
    private long roomId;

    public long getUserId() {return userId;}

    public String getFirstName() {return firstName;}

    public String getSecondName() {return secondName;}

    public String getImage_path() {return image_path;}

    public long getRoomId() {return roomId;}

    public RoomUser (String params){
        String[] param = params.split(",");
        this.userId = Long.parseLong(param[0]);
        this.firstName = param[1];
        this.secondName = param[2];
        this.image_path = param[3];
        this.roomId = Long.parseLong(param[4]);
    }
}
