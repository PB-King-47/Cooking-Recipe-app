package com.example.cookingrecipeapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Recipes_list_page extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list_page); // Make sure this file exists

        recyclerView = findViewById(R.id.recyclerViewRecipeList);// recyclerViewDesserts
        recipeList = new ArrayList<>();

        // Dummy recipes - add your own or fetch from API
        recipeList.add(new Recipe("Chocolate Cake", R.drawable.ic_launcher_background));
        recipeList.add(new Recipe("Vanilla Ice Cream", R.drawable.ic_launcher_background));
        recipeList.add(new Recipe("Strawberry Tart", R.drawable.ic_launcher_background));

        adapter = new RecipeAdapter(recipeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
