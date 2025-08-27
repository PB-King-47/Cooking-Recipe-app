package com.example.cookingrecipeapplication;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecipesDataUtils {

    public static List<Recipe> loadCardDataFromJSON(Context context, int userId) {
        List<Recipe> allRecipes = new ArrayList<>();
        List<Integer> favoriteIds = new ArrayList<>();

        try {
            // Load recipe data
            InputStream is = context.getAssets().open("data.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String jsonStr = new String(buffer, "UTF-8");

            JSONObject root = new JSONObject(jsonStr);
            JSONArray recipesArray = root.getJSONArray("recipes");

            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject obj = recipesArray.getJSONObject(i);
                allRecipes.add(new Recipe(
                    obj.getInt("id"),
                    obj.getInt("category_id"),
                    obj.getString("title"),
                    obj.getString("image")
                ));
            }

            // Load userData.json to get favorite IDs
            InputStream userStream = context.getAssets().open("userData.json");
            byte[] userBuffer = new byte[userStream.available()];
            userStream.read(userBuffer);
            userStream.close();
            String userJsonStr = new String(userBuffer, "UTF-8");

            JSONObject userRoot = new JSONObject(userJsonStr);
            JSONArray favArray = userRoot.getJSONArray("favorite");

            for (int i = 0; i < favArray.length(); i++) {
                JSONObject fav = favArray.getJSONObject(i);
                if (fav.getInt("user_id") == userId) {
                    JSONArray favList = fav.getJSONArray("fav_recipe_list_id");
                    for (int j = 0; j < favList.length(); j++) {
                        favoriteIds.add(favList.getInt(j));
                    }
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Filter recipes by favorite ID
        List<Recipe> favRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (favoriteIds.contains(recipe.getId())) {
                favRecipes.add(recipe);
            }
        }

        return favRecipes;
    }
}
