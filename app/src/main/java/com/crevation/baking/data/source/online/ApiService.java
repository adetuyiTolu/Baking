package com.crevation.baking.data.source.online;



import com.crevation.baking.data.model.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Adetuyi Tolu
 */

public interface ApiService {

    @GET("android-baking-app-json")
    Call<ArrayList<Recipe>> getRecipes();
}
