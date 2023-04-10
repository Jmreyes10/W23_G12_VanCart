package com.example.w23_g12_ecommerceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VER = 1;

    private static final String DATABASE_NAME = "user_sqlite";

    private static final String TABLE_NAME = "USERS";

    public static final String ID = "id";
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_ADDRESS = "userAddress";
    private SQLiteDatabase sqLiteDatabase;


    private static final String CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT NOT NULL, " + USER_EMAIL + " TEXT NOT NULL, "
            + USER_ADDRESS + " TEXT NOT NULL);";


    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
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

    public void addUser(UserModel userModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDBHelper.USER_NAME, userModel.getUserName());
        contentValues.put(UserDBHelper.USER_EMAIL, userModel.getUserEmail());
        contentValues.put(UserDBHelper.USER_ADDRESS, userModel.getUserAddress());

        sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.insert(UserDBHelper.TABLE_NAME,null, contentValues);
    }

    public List<UserModel> getUser(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<UserModel> userModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToNext()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String address = cursor.getString(3);
                userModels.add(new UserModel(id,name,email,address));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return userModels;
    }

    public void updateUser(UserModel userModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDBHelper.USER_NAME, userModel.getUserName());
        contentValues.put(UserDBHelper.USER_EMAIL, userModel.getUserEmail());
        contentValues.put(UserDBHelper.USER_ADDRESS, userModel.getUserAddress());
        sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.update(UserDBHelper.TABLE_NAME,contentValues,ID+ " = ?", new String
                []{String.valueOf(userModel.getId())});

    }

    public void deleteUser(int id){
        sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.delete(UserDBHelper.TABLE_NAME,ID+ " = ?", new String
                []{String.valueOf(id)});
    }

//    public int GetId(String userEmail){
//        sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id FROM USERS WHERE userEmail=?", new String[]{userEmail}))
//    }
}
