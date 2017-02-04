package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.DatabaseManager.CREATE_TABLE_ORDERS;
import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.DatabaseManager.CREATE_TABLE_PIZZAS;
import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.DatabaseManager.CREATE_TABLE_SIDES;
import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.DatabaseManager.DB_NAME;

import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.DatabaseManager.DB_TABLE_PIZZAS;
import static com.example.harry.mad_assignment1_harrisonjenkins_17967352.DatabaseManager.DB_VERSION;

/**
 * Created by Harry on 28-Dec-16.
 */

public class SQLHelper extends SQLiteOpenHelper {
    public SQLHelper (Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PIZZAS);
        db.execSQL(CREATE_TABLE_SIDES);
        db.execSQL(CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Pizzas table", "Upgrading database i.e. dropping table and re-creating it");
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PIZZAS);
        onCreate(db);
    }
}
