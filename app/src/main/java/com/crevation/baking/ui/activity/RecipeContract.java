package com.crevation.baking.ui.activity;

import com.crevation.baking.data.model.Recipe;
import com.crevation.baking.ui.BasePresenter;
import com.crevation.baking.ui.BaseView;

import java.util.ArrayList;

/**
 * Created by toluadetuyi on 6/25/17.
 */

public interface RecipeContract {

    interface View extends BaseView<Presenter> {

        void setRecipeList(ArrayList<Recipe> recipeList);
        void testToRest();
    }

    interface Presenter extends BasePresenter {

        void loadRecipe();



    }
}
