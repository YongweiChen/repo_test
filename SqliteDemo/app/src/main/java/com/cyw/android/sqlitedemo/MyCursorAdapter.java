package com.cyw.android.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by FH on 2017/2/18.
 */

public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.student_item, null);
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView id = (TextView) view.findViewById(R.id.tv_id);
        TextView name = (TextView) view.findViewById(R.id.tv_name);
        TextView age = (TextView) view.findViewById(R.id.tv_age);
        id.setText(String.valueOf(
                cursor.getInt(cursor.getColumnIndex(MySqlHelper.ID))));
        name.setText(cursor.getString(cursor.getColumnIndex(MySqlHelper.NAME)));
        age.setText(String.valueOf(
                cursor.getInt(cursor.getColumnIndex(MySqlHelper.AGE))));

    }
}
