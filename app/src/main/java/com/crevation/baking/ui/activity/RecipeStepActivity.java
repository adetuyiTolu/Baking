/**
 * Adetuyi Tolu
 */

package com.crevation.baking.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class RecipeStepActivity extends AppCompatActivity implements StepFragment.StepListener,
        RecipeStepContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Nullable
    @BindView(R.id.detail_container)
    FrameLayout mDetailFrame;

    @Nullable
    @BindView(R.id.txt_help)
    TextView mTabHelp;

    private Recipe mRecipe;

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

        if (getIntent().getParcelableExtra(Constants.RECIPE_EXTRA) != null) {
            mRecipe = getIntent().getParcelableExtra(Constants.RECIPE_EXTRA);
            getSupportActionBar().setTitle(mRecipe.getName());
        }
        showRecipe();
    }

    private void showRecipe() {

        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.RECIPE_EXTRA, mRecipe);
        RecipeFragment recipeStepFragment = new RecipeFragment();

        if (getSupportFragmentManager().findFragmentByTag(Constants.STEP_TAG) == null) {

            recipeStepFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_container, recipeStepFragment, Constants.STEP_TAG);
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
    public void itemClicked(Step step) {

        if (mDetailFrame != null) {

            if (mTabHelp != null) {
                mTabHelp.setVisibility(View.GONE);
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
