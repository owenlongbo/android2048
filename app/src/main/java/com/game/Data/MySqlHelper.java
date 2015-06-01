package com.game.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by longlong on 2015/4/28.
 */
public class MySqlHelper extends SQLiteOpenHelper {
    public MySqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行添加数据库语句
        db.execSQL("create table if not exists charts ( id integer primary key, user_name varchar, user_score integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
