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






/*

package com.example.cookingrecipeapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_INGREDIENT = 0;
    private static final int TYPE_STEP = 1;

    private final List<String> ingredients;
    private final List<String> steps;

    public RecipeDetailAdapter(List<String> ingredients, List<String> steps) {
        this.ingredients = ingredients;
        this.steps = steps;
    }

    @Override
    public int getItemViewType(int position) {
        return position < ingredients.size() ? TYPE_INGREDIENT : TYPE_STEP;
    }

    @Override
    public int getItemCount() {
        return ingredients.size() + steps.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_INGREDIENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
            return new IngredientViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_step, parent, false);
            return new StepViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_INGREDIENT) {
            ((IngredientViewHolder) holder).bind(ingredients.get(position));
        } else {
            int stepIndex = position - ingredients.size();
            ((StepViewHolder) holder).bind((stepIndex + 1) + ". " + steps.get(stepIndex));
        }
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.itemIngredientView);
        }

        void bind(String item) {
            text.setText(item);
        }
    }

    static class StepViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        StepViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.itemStepView);
        }

        void bind(String item) {
            text.setText(item);
        }
    }
}*/
