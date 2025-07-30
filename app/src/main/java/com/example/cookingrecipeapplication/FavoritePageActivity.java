package com.example.cookingrecipeapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritePageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private List<Recipe> favoriteRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_page);

        recyclerView = findViewById(R.id.favoriteDataList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);

        // For example, user_id = 1
        favoriteRecipes = RecipesDataUtils.loadCardDataFromJSON(this, 1);

        adapter = new FavoriteAdapter(favoriteRecipes,this);
        recyclerView.setAdapter(adapter);
    }
}

/*

public class FavoritePageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private List<Recipe> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.favoriteDataList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        recyclerView.setNestedScrollingEnabled(false);

        // Load JSON
        cardList = RecipesDataUtils.loadCardDataFromJSON(this);

        adapter = new FavoriteAdapter(cardList, this);
        recyclerView.setAdapter(adapter);
    }
}
*/

/*
EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
*/