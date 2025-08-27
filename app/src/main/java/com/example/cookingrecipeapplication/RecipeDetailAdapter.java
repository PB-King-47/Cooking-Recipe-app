package com.example.cookingrecipeapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.ViewHolder> {
    private final List<String> items;
    private final boolean isStep;

    public RecipeDetailAdapter(List<String> items, boolean isStep) {
        this.items = items;
        this.isStep = isStep;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = isStep ? R.layout.item_step : R.layout.item_ingredient;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.text.setText(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = isStepView(itemView) ? itemView.findViewById(R.id.itemStepView)
                    : itemView.findViewById(R.id.itemIngredientView);
        }

        private boolean isStepView(View itemView) {
            return itemView.findViewById(R.id.itemStepView) != null;
        }
    }
}
