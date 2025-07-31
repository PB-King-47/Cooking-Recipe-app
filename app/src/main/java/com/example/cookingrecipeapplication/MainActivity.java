package com.example.cookingrecipeapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardItem> cardList;
    ImageButton btnFavorites, profileImageButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "MainActivity loaded");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.categoryView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        recyclerView.setNestedScrollingEnabled(false);
        btnFavorites = findViewById(R.id.favoriteImageButton); // match the ID
        profileImageButton = findViewById(R.id.profileImageButton); // match the ID

        // Favorite button function
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start FavoritePageActivity
                Intent intent = new Intent(MainActivity.this, FavoritePageActivity.class);

                // If needed, pass user_id (e.g. 1 or 2)
                intent.putExtra("user_id", 1);

                startActivity(intent);
            }
        });

        // Profile button function
        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start FavoritePageActivity
                Toast.makeText(getApplicationContext(), "Profile clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        // Load JSON
        cardList = loadCardDataFromJSON();

        adapter = new CardAdapter(cardList);
        recyclerView.setAdapter(adapter);
    }

    private List<CardItem> loadCardDataFromJSON() {
        List<CardItem> items = new ArrayList<>();
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("categories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                int id = obj.getInt("id");
                String image = obj.getString("image");
                String name = obj.getString("name");

                items.add(new CardItem(id, image, name));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}








/*
package com.example.cookingrecipeapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}

 */