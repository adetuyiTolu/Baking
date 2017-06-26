/**
 * Adetuyi Tolu
 */


package com.crevation.baking.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.crevation.baking.R;
import com.crevation.baking.data.model.Recipe;
import com.crevation.baking.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.crevation.baking.util.Constants.ING_FRAG;
import static com.crevation.baking.util.Constants.STEP_FRAG;

public class RecipeFragment extends Fragment {


    @BindView(R.id.ingredient_frag)
    FrameLayout mIngredientView;

    @BindView(R.id.step_frag)
    FrameLayout mStepView;

    private Unbinder unbinder;
    private Recipe recipe;
    Fragment mIngredientFragment;
    Fragment mStepFragment;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getParcelable(Constants.RECIPE_EXTRA) != null) {
            recipe = getArguments().getParcelable(Constants.RECIPE_EXTRA);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        unbinder = ButterKnife.bind(this, view);

        setUpStepAndIngredient(savedInstanceState);
        return view;
    }


    private void setUpStepAndIngredient(Bundle savedInstanceState) {


        if (savedInstanceState != null) {

            mIngredientFragment = getActivity().getSupportFragmentManager()
                    .getFragment(savedInstanceState, ING_FRAG);
            mStepFragment = getActivity().getSupportFragmentManager()
                    .getFragment(savedInstanceState, STEP_FRAG);

        } else {

            Bundle ingredientBundle = new Bundle();
            ingredientBundle.putParcelableArrayList(Constants.BUNDLE_INGREDIENT, recipe.getIngredients());

            Bundle stepBundle = new Bundle();
            stepBundle.putParcelableArrayList(Constants.BUNDLE_STEP, recipe.getSteps());

            mIngredientFragment = new IngredientFragment();
            mIngredientFragment.setArguments(ingredientBundle);

            mStepFragment = new StepFragment();
            mStepFragment.setArguments(stepBundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ingredient_frag, mIngredientFragment).commit();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_frag, mStepFragment).commit();

        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (mIngredientFragment.isAdded()) {
            getActivity().getSupportFragmentManager().putFragment(outState,
                    ING_FRAG, mIngredientFragment);
        }
        if (mStepFragment.isAdded()) {
            getActivity().getSupportFragmentManager().putFragment(outState,
                    STEP_FRAG, mStepFragment);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

}
