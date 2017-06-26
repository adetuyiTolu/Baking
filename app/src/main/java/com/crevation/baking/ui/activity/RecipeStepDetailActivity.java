/**
 * Adetuyi Tolu
 */

package com.crevation.baking.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.crevation.baking.R;
import com.crevation.baking.data.model.Step;
import com.crevation.baking.ui.fragment.RecipeStepDetailFragment;
import com.crevation.baking.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String mRecipeName;
    private Step mStep;
    boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        checkOrientation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);
        ButterKnife.bind(this);
        getSavedData();

        if (!isLandscape) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mRecipeName);
        } else {
            toolbar.setVisibility(View.GONE);
        }

        showDetail();

    }

    private void checkOrientation() {
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape)
            addFullScreenParameters();
        else
            isLandscape = false;

    }

    private void addFullScreenParameters() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void getSavedData() {

        if (getIntent().getParcelableExtra(Constants.BUNDLE_STEP) != null) {
            mStep = getIntent().getParcelableExtra(Constants.BUNDLE_STEP);
        }

        if (getIntent().getStringExtra(Constants.RECIPE) != null) {
            mRecipeName = getIntent().getStringExtra(Constants.RECIPE);
        }

    }

    private void showDetail() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_STEP, mStep);

        if (getSupportFragmentManager().findFragmentByTag(Constants.STEP_DETAIL_TAG) == null) {

            RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
            recipeStepDetailFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_container, recipeStepDetailFragment, Constants.STEP_DETAIL_TAG);
            transaction.commit();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
