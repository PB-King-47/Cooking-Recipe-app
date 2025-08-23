package com.example.cookingrecipeapplication;

import java.util.List;

public class Recipe {
    private int id;
    private int category_id;
    private String title;
    private String image;
    private String prep_time;
    private String cook_level;
    private int servings;
    private List<String> ingredients;
    private List<String> steps;

    // Full Constructor
    public Recipe(int id, int category_id, String title, String image, String prep_time,
                  String cook_level, int servings, List<String> ingredients, List<String> steps) {
        this.id = id;
        this.category_id = category_id;
        this.title = title;
        this.image = image;
        this.prep_time = prep_time;
        this.cook_level = cook_level;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Recipe(int id, int category_id, String title, String image) {
        this.id = id;
        this.category_id = category_id;
        this.title = title;
        this.image = image;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return category_id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPrepTime() {
        return prep_time;
    }

    public String getCookLevel() {
        return cook_level;
    }

    public int getServings() {
        return servings;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }
}
