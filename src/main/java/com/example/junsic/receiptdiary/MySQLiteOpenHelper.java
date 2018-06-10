package com.example.junsic.receiptdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{//mysql helper

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //DB를 만듭니다.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + MainActivity.IDNAME+ "("
                +"date text,"
                +"type text,"
                +"name text,"
                +"money text);";
        db.execSQL(sql);
    }

    //DB가 업데이트 되면 드랍시킨다음에 업데이트 합니다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "drop table if exists student";
        db.execSQL(sql);
        onCreate(db);
    }
}
