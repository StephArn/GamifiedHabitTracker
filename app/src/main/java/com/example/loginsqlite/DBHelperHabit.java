package com.example.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelperHabit extends SQLiteOpenHelper  {

    public static final String DBNAME = "Login.db";

    public DBHelperHabit(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        //MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table habits(" +
                "habit_id INTEGER primary key AUTOINCREMENT, " +
                "name TEXT NOT NULL," +
             " description TEXT," +
                " habit_count INTEGER NOT NULL DEFAULT 0)" +
                "");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists habits");
        //MyDB.execSQL("drop Table if exists habits");
    }

    public Boolean insertUser(String name, String description, Integer habit_count){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("habit_count", habit_count);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else return true;

    }

//    public Boolean checkusername(String username){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
//        return cursor.getCount() > 0;
//
//    }
//
//    public Boolean checkusernamepassword(String username, String password){
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
//        return cursor.getCount() > 0;
//    }
}
