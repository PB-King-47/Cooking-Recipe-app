package com.example.cookingrecipeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView cookTimeView, cookLevelView, titleView;
    ImageView imageView;
    RecyclerView ingListView, detailsListView;
    int recipeId;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        btnBack = findViewById(R.id.buttonBackHome);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Get ID from intent
        recipeId = getIntent().getIntExtra("id", -1);

        // Bind Views
        cookTimeView = findViewById(R.id.cookTimeView);
        cookLevelView = findViewById(R.id.cookLevelView);
        titleView = findViewById(R.id.rec_title_View);
        imageView = findViewById(R.id.imageView);
        ingListView = findViewById(R.id.ingListView);
        detailsListView = findViewById(R.id.detailsListView);

        ingListView.setLayoutManager(new LinearLayoutManager(this));
        detailsListView.setLayoutManager(new LinearLayoutManager(this));
        // Load data.json and find recipe by id
        Recipe recipe = findRecipeById(recipeId);
        if (recipe != null) {
            showRecipe(recipe);
        }
    }

    private void showRecipe(Recipe recipe) {
        titleView.setText(recipe.getTitle());
        cookTimeView.setText(recipe.getPrepTime());
        cookLevelView.setText(recipe.getCookLevel());

        // Load image from assets
        String assetPath = "file:///android_asset/image/recipe_img/" + recipe.getImage();
        Glide.with(this).load(assetPath).into(imageView);

        // Adapter for ingredients
        ingListView.setAdapter(new RecipeDetailAdapter(recipe.getIngredients(), false));

        // Adapter for steps
        detailsListView.setAdapter(new RecipeDetailAdapter(recipe.getSteps(), true));
    }

    private Recipe findRecipeById(int id) {
        try {
            // Load JSON from assets
            InputStream is = getAssets().open("data.json");
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String jsonString = scanner.hasNext() ? scanner.next() : "";

            Gson gson = new Gson();

            // Parse the root JSON object
            JsonObject root = gson.fromJson(jsonString, JsonObject.class);

            // Extract "recipes" array from it
            Type listType = new TypeToken<List<Recipe>>() {}.getType();
            List<Recipe> allRecipes = gson.fromJson(root.getAsJsonArray("recipes"), listType);

            // Search by ID
            for (Recipe recipe : allRecipes) {
                if (recipe.getId() == id) return recipe;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
