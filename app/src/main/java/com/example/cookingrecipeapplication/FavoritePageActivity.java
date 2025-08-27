package com.example.cookingrecipeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritePageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private List<Recipe> favoriteRecipes;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_page);

        recyclerView = findViewById(R.id.favoriteDataList);
        btnBack = findViewById(R.id.buttonBackHome);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);

        // For example, user_id = 1
        favoriteRecipes = RecipesDataUtils.loadCardDataFromJSON(this, 1);

        adapter = new FavoriteAdapter(favoriteRecipes,this);
        recyclerView.setAdapter(adapter);
    }
}
