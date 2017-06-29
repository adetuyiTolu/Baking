/**
 * Adetuyi Tolu
 */

package com.crevation.baking.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.crevation.baking.R;
import com.crevation.baking.ui.fragment.RecipeFragment;
import com.crevation.baking.ui.fragment.StepFragment;
import com.crevation.baking.data.model.Recipe;
import com.crevation.baking.data.model.Step;
import com.crevation.baking.ui.fragment.RecipeStepDetailFragment;
import com.crevation.baking.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.crevation.baking.util.Constants.ING_FRAG;
import static com.crevation.baking.util.Constants.RECIPE_EXTRA;
import static com.crevation.baking.util.Constants.STEP_FRAG;

public class RecipeStepActivity extends AppCompatActivity implements StepFragment.StepListener,
        RecipeStepContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Nullable
    @BindView(R.id.detail_container)
    FrameLayout mDetailFrame;

    @Nullable
    @BindView(R.id.txt_help)
    TextView mHelp;

    private Recipe mRecipe;

    Fragment mRecipeFragment;

    RecipeStepContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new RecipeStepPresenter(this);

        if (getIntent().getParcelableExtra(RECIPE_EXTRA) != null) {
            mRecipe = getIntent().getParcelableExtra(RECIPE_EXTRA);
            getSupportActionBar().setTitle(mRecipe.getName());
        }
        showRecipe(savedInstanceState);
    }

    private void showRecipe(Bundle savedInstanceState) {

        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_EXTRA, mRecipe);

        if (savedInstanceState != null) {

            mRecipeFragment = getSupportFragmentManager().getFragment(savedInstanceState, STEP_FRAG);

        } else {

            mRecipeFragment = new RecipeFragment();
            mRecipeFragment.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_container, mRecipeFragment, Constants.STEP_TAG);
            transaction.commit();
        }

    }

    @Override
    public void setPresenter(RecipeStepContract.Presenter presenter) {

        mPresenter = presenter;

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoader(boolean show) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();
                break;

            case R.id.menu_recipe:

                mPresenter.addIngredientToWidget(mRecipe);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.widget, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (mRecipeFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState,
                    STEP_FRAG, mRecipeFragment);
        }

    }

    @Override
    public void itemClicked(Step step) {

        if (mDetailFrame != null) {

            if (mHelp != null) {
                mHelp.setVisibility(View.GONE);
            }

            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.BUNDLE_STEP, step);

            RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
            recipeStepDetailFragment.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, recipeStepDetailFragment, Constants.DETAIL_TAG);
            transaction.commit();

        } else {
            Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(Constants.BUNDLE_STEP, step);
            intent.putExtra(Constants.RECIPE, mRecipe.getName());
            startActivity(intent);
        }
    }


}
