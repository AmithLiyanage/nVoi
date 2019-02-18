package com.nvoi.nvoi_new.Model;

public class User {

    private String id;
    private String username;
    private String email;
    private String imageURL;
    private String status;
    private String search;
    private double rating;
    private int noOfTransport;

    public User(String id, String username, String email, String imageURL, String status, String search, double rating, int noOfTransport) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
        this.rating = rating;
        this.noOfTransport = noOfTransport;
    }

    public User() {

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNoOfTransport() {
        return noOfTransport;
    }

    public void setNoOfTransport(int noOfTransport) {
        this.noOfTransport = noOfTransport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
