package com.example.cookingrecipeapplication;

public class CardItem {
    private String imageAssetPath;
    private String title;

    public CardItem(String imageAssetPath, String title) {
        this.imageAssetPath = imageAssetPath;
        this.title = title;
    }

    public String getImageAssetPath() {
        return imageAssetPath;
    }

    public String getTitle() {
        return title;
    }
}
