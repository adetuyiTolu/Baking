/*
 * Adetuyi Tolu
 */

package com.crevation.baking.data.model;

import android.os.Parcel;
import android.os.Parcelable;



public class Ingredient implements Parcelable {

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {

        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }

    };


    private String quantity;
    private String measure;
    private String ingredient;

    public Ingredient() {
    }

    protected Ingredient(Parcel in) {

        this.quantity = in.readString();
        this.measure = in.readString();
        this.ingredient = in.readString();

    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {

        this.quantity = quantity;

    }

    public String getMeasure() {

        return measure;
    }

    public void setMeasure(String measure) {

        this.measure = measure;

    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {

        this.ingredient = ingredient;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}
