package com.crevation.baking;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.contrib.RecyclerViewActions;


import com.crevation.baking.ui.activity.RecipeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Adetuyi Tolu
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    private IdlingResource mIdlingResource;


    @Rule
    public ActivityTestRule<RecipeActivity> activityTestRule= new ActivityTestRule<>(RecipeActivity.class);

    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    @Before
    public void registerIdlingResource() {

        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        // Register Idling Resources
        Espresso.registerIdlingResources(mIdlingResource);

    }

    @Test
    public void RecipeListRecyclerViewIsDisplayed(){

        // Check that the Recycler View is  displayed
        onView(withId(R.id.recipe_list_recycler)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRecyclerViewItemOpensRecipeStepListActivity(){

        // Click on the RecyclerView item at position 1
        onView(withId(R.id.recipe_list_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


        onView(withId(R.id.recipe_container)).check(matches(isDisplayed()));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}
