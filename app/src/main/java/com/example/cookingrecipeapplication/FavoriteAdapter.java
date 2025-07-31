package com.example.cookingrecipeapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private Context context;

    public FavoriteAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe_favorite_card, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.name.setText(recipe.getTitle());

        String assetPath = "file:///android_asset/image/recipe_img/" + recipe.getImage();

        Glide.with(holder.image.getContext())
                .load(Uri.parse(assetPath)) // ← this is important
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailActivity.class); // ✅ Go to detail activity
            intent.putExtra("id", recipe.getId());
            intent.putExtra("recipe_name", recipe.getTitle());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageRecipe);
            name = itemView.findViewById(R.id.textRecipeName);
        }
    }
}

