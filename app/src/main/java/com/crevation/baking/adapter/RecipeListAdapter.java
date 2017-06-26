package com.crevation.baking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crevation.baking.R;
import com.crevation.baking.app.BakingApp;
import com.crevation.baking.data.model.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adetuyi Tolu
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipeArrayList;
    private ItemSelectedListener recipeSelectedListener;


    public RecipeListAdapter(ArrayList<Recipe> recipeArrayList, ItemSelectedListener recipeSelectedListener) {
        this.recipeArrayList = recipeArrayList;
        this.recipeSelectedListener = recipeSelectedListener;
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        @BindView(R.id.recipe_name)
        TextView recipe_name;
        @BindView(R.id.recipe_ingredient)
        TextView recipe_ingredient;
        @BindView(R.id.recipe_step)
        TextView recipe_step;
        @BindView(R.id.recipe_img)
        ImageView recipe_img;

        public RecipeViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            recipeSelectedListener.onRecipeSelected(position);
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_recipe, viewGroup, false);

        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe = recipeArrayList.get(i);

        String ingredient = recipe.getIngredients().size() + " " + "Ingredients";
        String steps = recipe.getSteps().size() + " " + "Steps";

        recipeViewHolder.recipe_name.setText(recipe.getName());
        recipeViewHolder.recipe_ingredient.setText(ingredient);
        recipeViewHolder.recipe_step.setText(steps);

        if (recipe.getImage() != null && !recipe.getImage().equals(""))
            BakingApp.loadImageFromResourceInto(BakingApp.getInstance(), recipeViewHolder.recipe_img,
                    recipe.getImage());

    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    public interface ItemSelectedListener {

        void onRecipeSelected(int position);

    }
}
