package com.example.w23_g12_ecommerceapp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderContentProvider extends ContentProvider {

    public static final int ORDER = 100;
    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(OrderContract.CONTENT, OrderContract.PATH,ORDER);
    }

    public OrderHelper orderHelper;
    @Override
    public boolean onCreate() {
        orderHelper = new OrderHelper(getContext());
        return true;
    }

    
    @Override
    public Cursor query( Uri uri,  String[] strings,  String s,  String[] strings1,  String s1) {
        SQLiteDatabase liteDatabase = orderHelper.getReadableDatabase();
        Cursor cursor;
        int found = uriMatcher.match(uri);
        switch (found){
            case ORDER:
                cursor = liteDatabase.query(OrderContract.OrderEntry.TABLE_NAME, strings, s, strings1, null,null, s1);
                break;

            default:
                throw new IllegalArgumentException("Query was Unsuccessful");
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    
    @Override
    public String getType( Uri uri) {
        return null;
    }

    
    @Override
    public Uri insert( Uri uri,  ContentValues contentValues) {
        int found = uriMatcher.match(uri);
        switch (found){
            case ORDER:
                return insertedOrder(uri, contentValues);

            default:
                Log.d("SQLError", "Can not insert");
        }
        return null;
    }

    private Uri insertedOrder(Uri uri, ContentValues contentValues) {
        String prodName = contentValues.getAsString(OrderContract.OrderEntry.COLUMN_NAME);
        if (prodName == null) {
            Log.d("SQLError", "Prod Name is null");
        }

//        String prodQuantity = contentValues.getAsString(OrderContract.OrderEntry.COLUMN_QUANTITY);
//        if (prodQuantity == null) {
//            Log.d("SQLError", "Prod Name is null");
//        }

        String prodPrice = contentValues.getAsString(OrderContract.OrderEntry.COLUMN_PRICE);
        if (prodPrice == null) {
            Log.d("SQLError", "Prod Name is null");
        }

        SQLiteDatabase liteDatabase = orderHelper.getWritableDatabase();
        long id = liteDatabase.insert(OrderContract.OrderEntry.TABLE_NAME, null, contentValues);

        if (id == -1) {
            return null;
        }
            getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri,  String s,  String[] strings) {

        int rowDeleted = 0;
        SQLiteDatabase liteDatabase = orderHelper.getWritableDatabase();
        int found = uriMatcher.match(uri);
        switch (found){
            case ORDER:
                rowDeleted = liteDatabase.delete(OrderContract.OrderEntry.TABLE_NAME,s,strings);
                break;

            default:
                Log.d("SQLError", "Deletion failed");
        }

        if (rowDeleted != 0) {
                getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
