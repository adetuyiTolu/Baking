/*
 * @author Adetuyi Tolu
 * Base view class
 *
 */

package com.crevation.baking.ui;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showMessage(String message);

    void showLoader(boolean show);

}
