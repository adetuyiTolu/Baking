/**
 * Adetuyi Tolu
 */


package com.crevation.baking.ui.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.crevation.baking.R;
import com.crevation.baking.adapter.IngredientListAdapter;
import com.crevation.baking.data.model.Ingredient;
import com.crevation.baking.util.BakingRecycler;
import com.crevation.baking.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.crevation.baking.util.Constants.ING_BUNDLE;
import static com.crevation.baking.util.Constants.ING_FRAG;
import static com.crevation.baking.util.Constants.STEP_BUNDLE;


public class IngredientFragment extends Fragment {

    @BindView(R.id.ingredient_recycler)
    RecyclerView ingredient_recycler;
    private ArrayList<Ingredient> ingredientArrayList;
    private Unbinder unbinder;
    Parcelable mListState;
    int itemPosition;

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
        ingredient_recycler.setAdapter(ingredientListAdapter);
        ingredient_recycler.getLayoutManager().smoothScrollToPosition(ingredient_recycler,
                null, itemPosition);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        mListState = ingredient_recycler.getLayoutManager().onSaveInstanceState();
        itemPosition = ((LinearLayoutManager) ingredient_recycler.getLayoutManager())
                .findLastVisibleItemPosition();
        outState.putParcelable(ING_BUNDLE, mListState);
        outState.putInt("position", itemPosition);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(ING_BUNDLE);
            itemPosition = savedInstanceState.getInt("position");
            ingredient_recycler.getLayoutManager().onRestoreInstanceState(mListState);
            ingredient_recycler.getLayoutManager().smoothScrollToPosition(ingredient_recycler,
                    null, itemPosition-1);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
