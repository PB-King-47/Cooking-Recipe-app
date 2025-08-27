package com.example.cookingrecipeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;

public class Recipes_list_page extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    private int selectedCatID;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list_page);

        btnBack = findViewById(R.id.buttonBackHome);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Get category name & id from Intent
        Intent intent = getIntent();
        String catName = intent.getStringExtra("name");
        selectedCatID = intent.getIntExtra("id", -1);  // <-- Fix here

        // Setup UI
        TextView categoryTitle = findViewById(R.id.resDetailsName);
        recyclerView = findViewById(R.id.recyclerViewRecipeList);
        recipeList = new ArrayList<>();

        categoryTitle.setText(catName);

        // Load JSON with filtering
        recipeList = loadCardDataFromJSON();

        // Setup RecyclerView
        adapter = new RecipeAdapter(recipeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    private List<Recipe> loadCardDataFromJSON() {
        List<Recipe> items = new ArrayList<>();
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("recipes");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                int id = obj.getInt("id");
                int category_id = obj.getInt("category_id");
                String title = obj.getString("title");
                String image = obj.getString("image");

                if (category_id == selectedCatID) {
                    items.add(new Recipe(id, category_id, title, image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
