/**
 * Adetuyi Tolu
 */


package com.crevation.baking.data.source.local;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class IngredientContract {

    public static final String DB_NAME = "com.crevation.baking.data.local.ingredient";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "ingredient";
    public static final String AUTHORITY = "com.crevation.baking.widget";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    public static final int LIST_INGREDIENT = 1;
    public static final int ITEM_INGREDIENT_ = 2;
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/crevation.ingredientDB/" + TABLE;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/crevation/ingredientDB" + TABLE;

    public class Columns {

        public static final String _ID = BaseColumns._ID;
        public static final String QUANTITY = "quantity";
        public static final String INGREDIENT = "ingredient";
        public static final String MEASURE = "measure";

    }
}
