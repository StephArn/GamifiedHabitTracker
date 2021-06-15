package com.example.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper  {

    private static final String DBNAME = "Login.db";
    static private DBHelper instance;

    private DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    static public DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        //MyDB.execSQL("create Table habits(habit_id INTEGER primary key, name TEXT NOT NULL," +
          //      " description TEXT, habit_count INTEGER NOT NULL DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        //MyDB.execSQL("drop Table if exists habits");
    }

    public Boolean insertUser(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else return true;

    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;

    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        return cursor.getCount() > 0;
    }

    public Boolean updateUsername(String oldUsername, String newUsername) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", newUsername);
        long result = MyDB.update("users", contentValues, "username = ?",  new String[]{oldUsername});
        return result != -1;
    }

    public Boolean updatePassword(String newPassword, String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        long result = MyDB.update("users",contentValues,"username = ?", new String[]{username});
        return result != -1;

    }
}
