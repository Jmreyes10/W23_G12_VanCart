package com.example.w23_g12_ecommerceapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VER = 1;
    public static String DATABASE_NAME ="orders.db";

    public OrderHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_Tb = "CREATE TABLE " + OrderContract.OrderEntry.TABLE_NAME + " ( " +
                OrderContract.OrderEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OrderContract.OrderEntry.COLUMN_NAME + "TEXT NOT NULL, " +
//                OrderContract.OrderEntry.COLUMN_QUANTITY + "TEXT NOT NULL, " +
                OrderContract.OrderEntry.COLUMN_PRICE + "TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_Tb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
