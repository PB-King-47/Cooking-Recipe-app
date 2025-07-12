package com.example.cookingrecipeapplication;

public class Recipe {
    private String name;
    private int imageResId; // Use String for image URL if loading from web

    public Recipe(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}

