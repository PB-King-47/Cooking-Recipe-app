package com.example.cookingrecipeapplication;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list_page); // Make sure this file exists

        Intent intent = getIntent(); // get the data form category function (cardAdaptor class)
        String catName = intent.getStringExtra("name");

        TextView categoryTitle = findViewById(R.id.catName);
        recyclerView = findViewById(R.id.recyclerViewRecipeList);// recyclerViewDesserts
        recipeList = new ArrayList<>();

        categoryTitle.setText(catName); // after back button text

        // Load JSON
        recipeList = loadCardDataFromJSON();

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

                items.add(new Recipe(id, category_id, title, image));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
