package com.example.abd_elrahman.nerby_places;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class DB_Sqlite extends SQLiteOpenHelper {


    public static final String DBname = "SingUp.db";

    public DB_Sqlite(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table logIn (id INTEGER PRIMARY KEY AUTOINCREMENT ,f_name TEXT," +
                "l_name TEXT,email TEXT ,phone INTEGER,gender TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS logIn");
        onCreate(sqLiteDatabase);
    }

    public boolean InsertData(String f_name,String l_name,String email,int phone,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("f_name",f_name);
        values.put("l_name",l_name);
        values.put("email",email);
        values.put("phone",phone);
        values.put("gender",gender);
        long result = db.insert("logIn",null,values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList getAllRecords(){

        ArrayList list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from logIn",null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            String col_id = res.getString(0);
            String col_Fname = res.getString(1);
            String col_Lname = res.getString(2);

            list.add(col_id+" "+col_Fname+" "+col_Lname);
            res.moveToNext();
        }
        return list;
    }


    boolean check(String Name,String Last) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "select * from logIn where f_name =? and l_name =?";

        Cursor res = db.rawQuery(select, new String[]{Name,Last});

        if (res.moveToFirst()) {
            return true;
        } else
            return false;
    }



    }
