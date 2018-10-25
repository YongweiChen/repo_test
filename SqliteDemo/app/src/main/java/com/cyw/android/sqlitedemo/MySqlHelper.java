package com.cyw.android.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FH on 2017/2/17.
 */

public class MySqlHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "students";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    private static final int DEFAULT_VERSION = 1;

    public MySqlHelper(Context context) {
        this(context, TABLE_NAME, null, DEFAULT_VERSION);
    }

    public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME + " (" +
                "_id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "age INTEGER " +
                ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
