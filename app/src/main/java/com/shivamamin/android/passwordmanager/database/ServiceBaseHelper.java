package com.shivamamin.android.passwordmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shivamamin.android.passwordmanager.database.ServiceDbSchema.ServiceTable;

/**
 * Created by Shivam Amin on 2018-01-14.
 */

public class ServiceBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "serviceBase.db";

    public ServiceBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ServiceTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            ServiceTable.Cols.UUID + ", " +
            ServiceTable.Cols.Service + ", " +
            ServiceTable.Cols.Username + ", " +
            ServiceTable.Cols.Password + ", " +
            ServiceTable.Cols.Description +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
