package com.example.w23_g12_ecommerceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

//
//    public DBHelper(Context context) {
//        super(context, "userdata", null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("create table Userdetails (name TEXT , phone TEXT primary key, address TEXT)");
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop table if exists Userdetails");
//    }
//
//    public Boolean saveUSerData(String name, String phone , String address){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("address", address);
//
//        long result = sqLiteDatabase.insert("Userdetails",null,contentValues);
//        if (result == -1) {
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    public Boolean updateAddress(String oldAddress, String name, String phone , String address){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("address", address);
//
//        long result = sqLiteDatabase.update("Userdetails",contentValues,"address=?", new String[]{oldAddress});
//        if (result == -1) {
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    public Cursor getText(){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from Userdetails", null);
//        return cursor;
//    }
public static final String DBNAME = "App.db";
    public DBHelper(Context context) {
        super(context, "App.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //MyDB.execSQL("create Table users(username TEXT primary key, fullname TEXT, password TEXT)");
        MyDB.execSQL("create Table orders(order_id INTEGER primary key autoincrement, customer_name TEXT, customer_address TEXT, customer_Phone TEXT, delivery_Status TEXT DEFAULT 'not delivered')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        //MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists orders");
    }

    public Boolean insertData(String username,String fullname, String password ){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("fullname", fullname);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertOrder(String customerName, String customerAddress ,String customerPhone) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_name", customerName);
        contentValues.put("customer_address", customerAddress);
        contentValues.put("customer_Phone", customerPhone);
        //contentValues.put("delivery_Status", deliveryStatus);

        long result = MyDB.insert("orders", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean updatepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("password", password);
        long result = MyDB.update("users", contentValues,"username = ?",new String[] {username});
        if(result==-1) return false;
        else
            return true;
    }
}
