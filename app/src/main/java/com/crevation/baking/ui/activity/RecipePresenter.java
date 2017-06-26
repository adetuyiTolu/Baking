package com.crevation.baking.ui.activity;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.crevation.baking.app.BakingApp;
import com.crevation.baking.data.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Adetuyi Tolu
 */

public class RecipePresenter implements RecipeContract.Presenter {

    RecipeContract.View mView;
    Call mRecipeCall;

    public RecipePresenter(RecipeContract.View view) {

        this.mView = view;
        mRecipeCall = BakingApp.getInstance().getApiService().getRecipes();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public Activity getView() {
        return (Activity) mView;
    }

    @Override
    public void loadRecipe() {

        checkInternetAccess();

        mView.showLoader(true);



        mRecipeCall.clone().enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                ArrayList<Recipe> mRecipeList = response.body();
                mView.setRecipeList(mRecipeList);
                mView.showLoader(false);

                mView.testToRest();

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

                mView.showLoader(false);
                mView.showMessage("Error retrieving recipes, swipe to refresh");
            }
        });
    }

    void checkInternetAccess() {

        if (!BakingApp.isOnline(getView().getApplicationContext())) {

            mView.showMessage("No internet access, swipe to refresh");
            return;

        }
    }
}
