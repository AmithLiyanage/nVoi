package com.nvoi.nvoi_new;

public class SuggestedTransporters {

    private String name;
    private String description;
    private double rating;
    private String imageUri;

    public SuggestedTransporters() {

    }

    public SuggestedTransporters(String name, String description, double rating, String imageUri) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
