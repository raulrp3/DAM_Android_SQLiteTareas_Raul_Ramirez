package com.example.alumno_fp.app_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

public class UtilsBD {

    private SQLiteDatabase db;

    public UtilsBD(SQLiteDatabase db) {
        this.db = db;
    }

    public List<Chore> readDb(List<Chore> listChore){
        Cursor cursor = db.rawQuery("SELECT id,title,description,date FROM Chores",null);
        if (cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String date = cursor.getString(3);

                listChore.add(new Chore(id,title,description,date));
            }while(cursor.moveToNext());
        }

        return listChore;
    }

    public void writeDb(String title,String description,String date){
        try{
            ContentValues newRegister = new ContentValues();
            newRegister.put("title",title);
            newRegister.put("description",description);
            newRegister.put("date",date);

            db.insert("Chores",null,newRegister);
        }catch (Exception ex){
            Log.e("ErrorDB", ex.getMessage());
        }
    }

    public void deleteDb(int id){
        db.delete("Chores","id = " + id,null);
    }

    public void updateDb(int id,String title,String description,String date){
        ContentValues updateRegister = new ContentValues();
        updateRegister.put("title", title);
        updateRegister.put("description",description);
        updateRegister.put("date",date);

        db.update("Chores",updateRegister,"id = " + id,null);
    }

    public int getLastID(){
        Cursor cursor = db.rawQuery("SELECT id FROM Chores",null);
        int id = 0;
        if (cursor.moveToLast()){
            id = cursor.getInt(0);
        }

        return id;
    }
}
