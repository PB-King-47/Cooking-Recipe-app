package com.example.cookingrecipeapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import android.content.Intent;

import java.util.List;
// This card Adapter is category adapter
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<CardItem> itemList;

    public CardAdapter(List<CardItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem item = itemList.get(position);
        holder.textView.setText(item.getName());

        String assetPath = "file:///android_asset/image/" + item.getImage();

        Glide.with(holder.imageView.getContext())
                .load(Uri.parse(assetPath)) // ← this is important
                .into(holder.imageView);

        // Category button on click to pass data
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Recipes_list_page.class);
            intent.putExtra("id", item.getId());
            intent.putExtra("name", item.getName());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardImage);
            textView = itemView.findViewById(R.id.cardTitle);
        }
    }
}
