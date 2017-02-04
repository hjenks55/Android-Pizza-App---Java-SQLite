package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Harry on 08-Jan-17.
 */




public class oldDatabaseManager {
    public static final String DB_NAME = "menu";
    public static final String DB_TABLE = "pizzas";
    public static final int DB_VERSION = 1;

    //SQL query create table with columns
    public static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE
            + " (id INTEGER PRIMARY KEY, product_name TEXT, product_description TEXT, price FLOAT);";

    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    //Constructor opens database in a writable mode
    public oldDatabaseManager(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    //opens database in readable mode
    public oldDatabaseManager openReadable() throws android.database.SQLException {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    //close database
    public void close() {
        helper.close();
    }

    //adding a new row with columns, input from arguments using ContentValues class
    public void addRow(Integer i, String n, String d, Float p) {
        ContentValues newProduct = new ContentValues();

        newProduct.put("id", i);
        newProduct.put("product_name", n);
        newProduct.put("product_description", d);
        newProduct.put("price", p);
        try {
            db.insertOrThrow(DB_TABLE, null, newProduct);
        }

        //exception handler
        catch (Exception e) {
            Log.e("Error in inserting rows", e.toString());
            e.printStackTrace();
        }
    }

    public String retrieveRows() {
        String[] columns = new String[]{"id", "product_name", "product_description", "price"};

        String whereClaus = "id >= 0";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE, columns, whereClaus, null, null, null, order);
        String tablerows = "";
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            tablerows = tablerows + cursor.getInt(0) + ", " + cursor.getString(1) +
                    ", " + cursor.getString(2) + ", " + cursor.getFloat(3) + "\n";
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return tablerows;
    }

    public String retrievePizza() {
        String[] columns = new String[]{"id", "product_name", "product_description", "price"};

        String whereClaus = "id == 1";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE, columns, whereClaus, null, null, null, order);
        String tablerows = "";
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            tablerows = cursor.getString(1);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return tablerows;
    }

    public String retrieveToppings() {
        String[] columns = new String[]{"id", "product_name", "product_description", "price"};

        String whereClaus = "id == 1";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE, columns, whereClaus, null, null, null, order);
        String tablerows = "";
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            tablerows = cursor.getString(2);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return tablerows;
    }

    public ArrayList<String> retrieveListViewRows() {// return an ArrayList instead of String
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[]{"id", "product_name", "product_description", "price"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            productRows.add(cursor.getString(1));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }


    public String retrievePizzas() {

        String[] columns = new String[]{"id", "product_name", "product_description", "price"};

        String whereClaus = "id >= 103";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE, columns, whereClaus, null, null, null, order);

        String tablerows = "";
        return tablerows;
    }

}


