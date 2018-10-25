package com.cyw.android.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FH on 2017/2/17.
 */

public class SqlManager {
    private MySqlHelper mMySqlHelper;
    private static SqlManager mInstance;

    public static SqlManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SqlManager(context);
        }
        return mInstance;
    }

    public SqlManager(Context context) {
        mMySqlHelper = new MySqlHelper(context);
    }

    public void addStudent(Student student) {
        SQLiteDatabase sd = mMySqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySqlHelper.ID, student.getId());
        values.put(MySqlHelper.NAME, student.getName());
        values.put(MySqlHelper.AGE, student.getAge());
        sd.insert(MySqlHelper.TABLE_NAME, null, values);
    }

    public Student queryStudent(String id, String name, String age) {
        SQLiteDatabase sd = mMySqlHelper.getWritableDatabase();
        StringBuilder selectWhere = new StringBuilder();
        boolean hasValue = false;
        if (!Utils.isEmpty(id)) {
            selectWhere.append(MySqlHelper.ID).append("=").append(id);
            hasValue = true;
        }
        if (!Utils.isEmpty(name)) {
            if (hasValue) {
                selectWhere.append(" AND ");
            }
            selectWhere.append(MySqlHelper.NAME).append("=").append("'").append(name).append("'");
            hasValue = true;
        }
        if (!Utils.isEmpty(age)) {
            if (hasValue) {
                selectWhere.append(" AND ");
            }
            selectWhere.append(MySqlHelper.AGE).append("=").append(age);
        }
        Cursor cursor = sd.query(MySqlHelper.TABLE_NAME, null, selectWhere.toString(),
                null, null , null, null);
        int std_id = cursor.getInt(cursor.getColumnIndex(MySqlHelper.ID));
        String std_name = cursor.getString(cursor.getColumnIndex(MySqlHelper.NAME));
        int std_age = cursor.getInt(cursor.getColumnIndex(MySqlHelper.AGE));
        Student s = new Student(std_id, std_name, std_age);
        return s;
    }

    public Cursor queryStudentCursor(String id, String name, String age) {
        SQLiteDatabase sd = mMySqlHelper.getWritableDatabase();
        StringBuilder selectWhere = new StringBuilder();
        boolean hasValue = false;
        if (!Utils.isEmpty(id)) {
            selectWhere.append(MySqlHelper.ID).append("=").append(id);
            hasValue = true;
        }
        if (!Utils.isEmpty(name)) {
            if (hasValue) {
                selectWhere.append(" AND ");
            }
            selectWhere.append(MySqlHelper.NAME).append("=").append("'").append(name).append("'");
            hasValue = true;
        }
        if (!Utils.isEmpty(age)) {
            if (hasValue) {
                selectWhere.append(" AND ");
            }
            selectWhere.append(MySqlHelper.AGE).append("=").append(age);
            hasValue = true;
        }
        if (!hasValue) {
            return null;
        } else {
            return sd.query(MySqlHelper.TABLE_NAME, null, selectWhere.toString(),
                    null, null, null, null);
        }
    }

    private static List<Student> cursorToList(Cursor cursor) {
        List<Student> students = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MySqlHelper.ID));
            String name = cursor.getString(cursor.getColumnIndex(MySqlHelper.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(MySqlHelper.AGE));
            Student s = new Student(id, name, age);
            students.add(s);
        }
        return students;
    }

    public List<Student> queryAll() {
        SQLiteDatabase sd = mMySqlHelper.getWritableDatabase();
        //sd.execSQL("select * from" + MySqlHelper.TABLE_NAME);
        Cursor c = sd.query(MySqlHelper.TABLE_NAME, null, null,
                null, null, null, MySqlHelper.ID + " desc");
        return cursorToList(c);
    }

    public Cursor queryAllCursor() {
        SQLiteDatabase sd = mMySqlHelper.getWritableDatabase();
        //sd.execSQL("select * from" + MySqlHelper.TABLE_NAME);
        Cursor c = sd.query(MySqlHelper.TABLE_NAME, null, null,
                null, null, null, MySqlHelper.ID + " asc");
        return c;
    }
}
