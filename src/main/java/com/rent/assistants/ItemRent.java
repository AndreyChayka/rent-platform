package com.rent.assistants;

public class ItemRent{
    private long itemId;
    private String title;
    private double price;
    private String imagePath;
    private String startDate;
    private String endDate;

    public long getItemId() {return itemId;}

    public String getTitle() {return title;}

    public double getPrice() {return price;}

    public String getImagePath() {return imagePath;}

    public String getStartDate() {return startDate;}

    public String getEndDate() {return endDate;}

    public ItemRent(String params){
        String[] param = params.split(",");
        this.itemId = Long.parseLong(param[0]);
        this.title = param[1];
        this.price = Double.parseDouble(param[2]);
        this.imagePath = param[3];
        this.startDate = param[4].split(" ")[0];
        this.endDate = param[5].split(" ")[0];
    }
}
