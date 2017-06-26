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


import com.crevation.baking.R;
import com.crevation.baking.data.model.Recipe;
import com.crevation.baking.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeFragment extends Fragment {


    @BindView(R.id.ingredient_frag)
    FrameLayout mIngredientView;

    @BindView(R.id.step_frag)
    FrameLayout mStepView;

    private Unbinder unbinder;
    private Recipe recipe;

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

        setUpStepAndIngredient();
        return view;
    }


    private void setUpStepAndIngredient() {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BUNDLE_INGREDIENT, recipe.getIngredients());

        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList(Constants.BUNDLE_STEP, recipe.getSteps());

        IngredientFragment ingredientTabFragment = new IngredientFragment();
        ingredientTabFragment.setArguments(bundle);

        StepFragment stepTabFragment = new StepFragment();
        stepTabFragment.setArguments(bundle1);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.ingredient_frag, ingredientTabFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_frag, stepTabFragment).commit();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

}
