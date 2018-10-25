package com.cyw.android.sqlitedemo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by FH on 2017/2/10.
 */

public class MyActivity extends AppCompatActivity implements View.OnClickListener{

    private SqlManager mSqlManager;
    private EditText mEd_id;
    private EditText mEd_name;
    private EditText mEd_age;
    private ListView mListView;
    private MyCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSqlManager = SqlManager.getInstance(this);
        mEd_id = (EditText) findViewById(R.id.edt_id);
        mEd_name = (EditText) findViewById(R.id.edt_name);
        mEd_age = (EditText) findViewById(R.id.edt_age);
        mListView = (ListView) findViewById(R.id.listView);
        Button add_std = (Button) findViewById(R.id.btn_add_std);
        Button add_query = (Button) findViewById(R.id.btn_query_std);
        Button add_queryAll = (Button) findViewById(R.id.btn_query_all);
        add_std.setOnClickListener(this);
        add_query.setOnClickListener(this);
        add_queryAll.setOnClickListener(this);
        mAdapter = new MyCursorAdapter(this, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_std:
                addStudent();
                break;
            case R.id.btn_query_std:
                queryStudent();
                break;
            case R.id.btn_query_all:
                queryAll();
                break;
        }
    }

    private void queryStudent(){
        String id = mEd_id.getText().toString();
        String name = mEd_name.getText().toString();
        String age = mEd_age.getText().toString();
        //mSqlManager.queryStudent(id, name, age);
        Cursor cursor = mSqlManager.queryStudentCursor(id, name, age);
        mAdapter.changeCursor(cursor);
    }

    private void queryAll(){
        Cursor cursor = mSqlManager.queryAllCursor();
        mAdapter.changeCursor(cursor);
    }

    private void addStudent() {
        String id = mEd_id.getText().toString();
        String name = mEd_name.getText().toString();
        String age = mEd_age.getText().toString();
        if (!Utils.isEmpty(id) && !Utils.isEmpty(name) && !Utils.isEmpty(age)) {
            Student s = new Student(
                    Integer.valueOf(mEd_id.getText().toString()),
                    mEd_name.getText().toString(),
                    Integer.valueOf(mEd_age.getText().toString()));
            mSqlManager.addStudent(s);
            clearAllInput();
            Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "内容不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAllInput() {
        mEd_id.setText("");
        mEd_name.setText("");
        mEd_age.setText("");
    }

}
