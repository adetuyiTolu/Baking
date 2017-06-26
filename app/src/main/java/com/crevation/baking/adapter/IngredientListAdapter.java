package com.crevation.baking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crevation.baking.R;
import com.crevation.baking.data.model.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adetuyi Tolu
 */

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.RecipeViewHolder> {

    private ArrayList<Ingredient> ingredientArrayList;


    public IngredientListAdapter(ArrayList<Ingredient> ingredientArrayList) {
        this.ingredientArrayList = ingredientArrayList;
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingredient_name)
        TextView ingredient_name;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_ingredient, viewGroup, false);

        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {

        Ingredient ingredient = ingredientArrayList.get(i);
        String measure = ingredient.getQuantity() + " " + ingredient.getMeasure();

        recipeViewHolder.ingredient_name.setText(ingredient.getIngredient() + " (" + measure + ")");


    }

    @Override
    public int getItemCount() {
        return ingredientArrayList.size();
    }
}
