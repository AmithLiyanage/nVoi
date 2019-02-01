package com.nvoi.nvoi_new.Model;

public class Transporter {

    private String id;
    private String username;
    private  String description;
    private double rating;
//    private String imageURL;
    //private int noOfTransport;


    public Transporter() {

    }

    public Transporter(String id, String username, String description, double rating) {//, String imageURL
        this.id = id;
        this.username = username;
        this.description = description;
        this.rating = rating;
//        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }
}
