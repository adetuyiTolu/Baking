/**
 * Adetuyi Tolu
 */


package com.crevation.baking.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.crevation.baking.R;
import com.crevation.baking.adapter.IngredientListAdapter;
import com.crevation.baking.data.model.Ingredient;
import com.crevation.baking.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class IngredientFragment extends Fragment {

    @BindView(R.id.ingredient_recycler)
    RecyclerView ingredient_recycler;
    private ArrayList<Ingredient> ingredientArrayList;
    private Unbinder unbinder;

    public IngredientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getParcelableArrayList(Constants.BUNDLE_INGREDIENT) != null) {
            ingredientArrayList = getArguments().getParcelableArrayList(Constants.BUNDLE_INGREDIENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        unbinder = ButterKnife.bind(this, view);

        populateList();
        return view;
    }

    private void populateList() {

        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter(ingredientArrayList);
        ingredient_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //ingredient_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        ingredient_recycler.setAdapter(ingredientListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
