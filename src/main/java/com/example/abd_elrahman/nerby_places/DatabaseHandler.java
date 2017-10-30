package com.example.abd_elrahman.nerby_places;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DBname = "SingUp.db";

    public DatabaseHandler(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table favourites (id INTEGER PRIMARY KEY AUTOINCREMENT ,title TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS favourites");
        onCreate(sqLiteDatabase);
    }


    public boolean InsertData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",title);
        long result = db.insert("favourites",null,values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList getAllRecords(){

        ArrayList list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from favourites",null);
        res.moveToFirst();
        while (!res.isAfterLast()){
            String col_title = res.getString(0);

//      راجعهاااااااااااااااااااااااااااااااااااااااااااااااااااااااااااااااااااا



           // list.add(col_title);
            res.moveToNext();
        }
        return list;
    }



    boolean check(String Title) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "select * from favourites where title =?";

        Cursor res = db.rawQuery(select, new String[]{Title});

        if (res.moveToFirst()) {
            return true;
        } else
            return false;
    }

}
