package com.example.w23_g12_ecommerceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VER = 1;

    private static final String DATABASE_NAME = "order_history";

    private static final String TABLE_NAME = "ORDER_HISTORY";

    public static final String ID = "id";
    public static final String PROD_NAME = "prodName";
    public static final String PROD_QUANTITY = "prodQuantity";
    public static final String PROD_PRICE = "prodPrice";
    private SQLiteDatabase sqLiteDatabase;


    private static final String CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + PROD_NAME + " TEXT NOT NULL, " + PROD_QUANTITY + " TEXT NOT NULL, "
            + PROD_PRICE + " TEXT NOT NULL);";

    public OrderHistoryDBHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VER);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addOrder(OrderModel orderModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(OrderHistoryDBHelper.PROD_NAME, orderModel.getProdName());
        contentValues.put(OrderHistoryDBHelper.PROD_QUANTITY, orderModel.getProdQuantity());
        contentValues.put(OrderHistoryDBHelper.PROD_PRICE, orderModel.getProdPrice());

        sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.insert(OrderHistoryDBHelper.TABLE_NAME,null, contentValues);
    }

    public List<OrderModel> getOrderHistory(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<OrderModel> orderModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToNext()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String qty = cursor.getString(2);
                String price = cursor.getString(3);
                orderModels.add(new OrderModel(id,name,qty,price));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return orderModels;
    }
}
