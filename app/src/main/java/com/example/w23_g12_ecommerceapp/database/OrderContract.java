package com.example.w23_g12_ecommerceapp.database;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {
    public static final String CONTENT = "com.example.w23_g12_ecommerceapp";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT);
    public static final String PATH = "orderhistory";

    public static abstract class OrderEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI , PATH);
        public static final String TABLE_NAME = "orderhistory";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "prodName";
//        public static final String COLUMN_QUANTITY = "prodQuantity";
        public static final String COLUMN_PRICE = "price";

    }
}
