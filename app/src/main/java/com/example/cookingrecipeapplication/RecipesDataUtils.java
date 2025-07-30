package com.example.cookingrecipeapplication;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/*
public class RecipesDataUtils {
    public static  List<Recipe> loadCardDataFromJSON(Context context) {
        List<Recipe> items = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("data.json");
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
*/


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
