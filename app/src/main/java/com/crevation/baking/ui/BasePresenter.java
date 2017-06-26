/*
 * @author Adetuyi Tolu
 * Base presenter class
 *
 */

package com.crevation.baking.ui;

import android.app.Activity;

public interface BasePresenter {

    void subscribe();

    void unsubscribe();

    Activity getView();
}
