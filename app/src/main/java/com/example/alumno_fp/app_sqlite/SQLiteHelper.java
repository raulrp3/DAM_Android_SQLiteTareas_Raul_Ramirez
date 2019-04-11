package com.example.alumno_fp.app_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Chores(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR(50),description TEXT,date VARCHAR(20))";

    public SQLiteHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Chores");
        db.execSQL(sqlCreate);
    }
}
