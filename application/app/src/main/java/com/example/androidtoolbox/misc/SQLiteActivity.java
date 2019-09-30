package com.example.androidtoolbox.misc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.utils.LogUtil;

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        MyDB myDB = new MyDB(getApplication(), "user", null, 1);
        SQLiteDatabase writableDatabase = myDB.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from p_user", null);
        while (cursor.moveToNext())
        {
            String string = cursor.getString(1);
            LogUtil.d("-------------"+string);
        }

        writableDatabase.close();
    }


    private class MyDB extends SQLiteOpenHelper{
        public static final String SQL_CREATE="create table "+UserRecords.UserEntry.TABLE_NAME +
                "("+ UserRecords.UserEntry._ID +" INTEGER PRIMARY KEY,"+
                UserRecords.UserEntry.USER_NAME +" text,"+
                UserRecords.UserEntry.USER_PWD + " text);";

        public static final String DB_NAME="user.db";
        public static final int VERSION=1;
        public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DB_NAME, factory, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE);
            db.execSQL("insert into "+ UserRecords.UserEntry.TABLE_NAME+"("+ UserRecords.UserEntry.USER_NAME+","+ UserRecords.UserEntry.USER_PWD+ ") values ('tom','3333')");
            db.execSQL("insert into "+ UserRecords.UserEntry.TABLE_NAME+"("+ UserRecords.UserEntry.USER_NAME+","+ UserRecords.UserEntry.USER_PWD+ ") values ('jack','123')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
