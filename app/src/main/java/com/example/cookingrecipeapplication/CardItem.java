package com.example.cookingrecipeapplication;

// this CardItem is the Category item for home page
public class CardItem {
    private int id;
    private String image;
    private String name;

    public CardItem(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }
    public int getId() { return id; }
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
