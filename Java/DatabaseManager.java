package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Harry on 28-Dec-16.
 */

public class DatabaseManager {
    public static final String DB_NAME = "pizzaApp";
    public static final String DB_TABLE_PIZZAS = "pizzas";
    public static final String DB_TABLE_SIDES = "sides";
    public static final String DB_TABLE_ORDERS = "orders";
    public static final String DB_TABLE_USERS = "users";

    public static final int DB_VERSION = 2;

    //SQL query create tables
    public static final String CREATE_TABLE_PIZZAS = "CREATE TABLE " + DB_TABLE_PIZZAS
            + " (id INTEGER PRIMARY KEY, type TEXT, description TEXT, price FLOAT);";

    public static final String CREATE_TABLE_SIDES = "CREATE TABLE " + DB_TABLE_SIDES
            + " (id INTEGER PRIMARY KEY, type TEXT, description TEXT, price FLOAT);";

    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE " + DB_TABLE_ORDERS
            + " (orderId INTEGER PRIMARY KEY, userEmail TEXT, userAddress TEXT, totalPrice FLOAT, item1 TEXT, item1Qty INT, item2 TEXT, item2Qty INT, item3 TEXT, item3Qty INT, item4 TEXT, item4Qty INT, item5 TEXT, item5Qty INT, item6 TEXT, item6Qty INT);";

    private SQLHelper helper;
    public SQLiteDatabase db;
    private Context context;

    //Constructor opens database in a writable mode
    public DatabaseManager(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    //opens database in readable mode
    public DatabaseManager openReadable() throws android.database.SQLException {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    //close database
    public void close() {
        helper.close();
    }

    //adding a new row with columns, input from arguments using ContentValues class
    public void addPizzasRow(Integer i, String n, String d, Float p) {
        ContentValues newProduct = new ContentValues();

        newProduct.put("id", i);
        newProduct.put("type", n);
        newProduct.put("description", d);
        newProduct.put("price", p);
        try {
            db.insertOrThrow(DB_TABLE_PIZZAS, null, newProduct);
        }
        //exception handler
        catch (Exception e) {
            Log.e("Error in inserting rows", e.toString());
            e.printStackTrace();
        }
    }

    //adding a new row with columns, input from arguments using ContentValues class
    public void addSidesRow(Integer i, String n, String d, Float p) {
        ContentValues newProduct = new ContentValues();

        newProduct.put("id", i);
        newProduct.put("type", n);
        newProduct.put("description", d);
        newProduct.put("price", p);
        try {
            db.insertOrThrow(DB_TABLE_SIDES, null, newProduct);
        }
        //exception handler
        catch (Exception e) {
            Log.e("Error in inserting rows", e.toString());
            e.printStackTrace();
        }
    }

    public void addOrdersRow(Integer i, String e, String a, Float p, String i1, int i1q, String i2, int i2q, String i3, int i3q, String i4, int i4q, String i5, int i5q, String i6, int i6q) {
        ContentValues newProduct = new ContentValues();

        newProduct.put("orderId", i);
        newProduct.put("userEmail", e);
        newProduct.put("userAddress", a);
        newProduct.put("totalPrice", p);
        newProduct.put("item1", i1);
        newProduct.put("item1Qty", i1q);
        newProduct.put("item2", i2);
        newProduct.put("item2Qty", i2q);
        newProduct.put("item3", i3);
        newProduct.put("item3Qty", i3q);
        newProduct.put("item4", i4);
        newProduct.put("item4Qty", i4q);
        newProduct.put("item4", i4);
        newProduct.put("item4Qty", i4q);
        newProduct.put("item5", i5);
        newProduct.put("item5Qty", i5q);
        newProduct.put("item6", i6);
        newProduct.put("item6Qty", i6q);
        try {
            db.insertOrThrow(DB_TABLE_ORDERS, null, newProduct);
        }
        //exception handler
        catch (Exception ex) {
            Log.e("Error in inserting rows", ex.toString());
            ex.printStackTrace();
        }
    }

    public void updateItem(int currentOrder, int currentItem, String type, int qty) {

        ContentValues newValues = new ContentValues();

        String itemNo = "item"+currentItem;
        String itemNoQty ="item"+currentItem+"Qty";

        newValues.put(itemNo, type);
        newValues.put(itemNoQty, qty);
        db.update(DB_TABLE_ORDERS, newValues, "orderId="+currentOrder, null);

    }

    public void updateEmail(int currentOrder, String userEmail) {

        ContentValues newValues = new ContentValues();
        String email = userEmail;
        newValues.put(userEmail, email);
        db.update(DB_TABLE_ORDERS, newValues, "orderId="+currentOrder, null);

    }

    public int getCurrentOrderId() {
        int totalOrders ;
        //String[] columns = new String[]{"orderId"};
        String countQuery = "SELECT  * FROM " + DB_TABLE_ORDERS;

        Cursor cursor = db.rawQuery(countQuery, null);
        totalOrders = cursor.getCount();
        cursor.close();

        return totalOrders;
    }

    public String getItem1(int currentOrderId) {

        String[] columns = new String[]{"orderId", "userEmail", "userAddress", "totalPrice", "item1", "item1Qty", "item2", "item2Qty", "item3", "item3Qty", "item4", "item4Qty", "item5", "item5Qty", "item6", "item6Qty"};

        String whereClaus = "orderId. =="+currentOrderId;

        Cursor cursor = db.query(DB_TABLE_ORDERS, columns, whereClaus, null, null, null, null);
        String item1 = "";
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            item1 = cursor.getString(4);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return item1;
    }


    public int getItem1Qty(int currentOrderId) {

        String[] columns = new String[]{"orderId", "userEmail", "userAddress", "totalPrice", "item1", "item1Qty", "item2", "item2Qty", "item3", "item3Qty", "item4", "item4Qty", "item5", "item5Qty", "item6", "item6Qty"};

        String whereClaus = "orderId =="+currentOrderId;

        Cursor cursor = db.query(DB_TABLE_ORDERS, columns, whereClaus, null, null, null, null);
        int item1Qty = 0;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            item1Qty = cursor.getInt(5);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return item1Qty;
    }



    public String getItem2(int currentOrderId) {

        String[] columns = new String[]{"orderId", "userEmail", "userAddress", "totalPrice", "item1", "item1Qty", "item2", "item2Qty", "item3", "item3Qty", "item4", "item4Qty", "item5", "item5Qty", "item6", "item6Qty"};
        String whereClaus = "orderId =="+currentOrderId;
        Cursor cursor = db.query(DB_TABLE_ORDERS, columns, whereClaus, null, null, null, null);
        String item2 = "";
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            item2 = cursor.getString(6);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return item2;
    }

    public int getItem2Qty(int currentOrderId) {

        String[] columns = new String[]{"orderId", "userEmail", "userAddress", "totalPrice", "item1", "item1Qty", "item1", "item2", "item2Qty", "item3", "item3Qty", "item4", "item4Qty", "item5", "item5Qty", "item6", "item6Qty"};

        String whereClaus = "orderId =="+currentOrderId;

        Cursor cursor = db.query(DB_TABLE_ORDERS, columns, whereClaus, null, null, null, null);
        int item2Qty = 0;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            item2Qty = cursor.getInt(7);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return item2Qty;
    }


    public String retrievePizza() {
        String[] columns = new String[]{"id", "type", "description", "price"};

        String whereClaus = "id == 1";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE_PIZZAS, columns, whereClaus, null, null, null, order);
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

    public float retrievePizzaPrice(String item1) {
        String[] columns = new String[]{"id", "type", "description", "price"};
        String whereClaus = "type='"+item1+"'";

        Cursor cursor = db.query(DB_TABLE_PIZZAS, columns, whereClaus, null, null, null, null);

        float price = 0.0f;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            price = cursor.getFloat(3);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return price;
    }

    public String retrieveOrder() {
        String[] columns = new String[]{"orderId", "userEmail", "userAddress", "totalPrice", "item1", "item1Qty", "item1", "item2", "item2Qty", "item3", "item3Qty"};

        String whereClaus = "id >= 0";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE_ORDERS, columns, whereClaus, null, null, null, order);
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

    public String retrieveToppings() {
        String[] columns = new String[]{"id", "type", "description", "price"};

        String whereClaus = "id == 1";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE_PIZZAS, columns, whereClaus, null, null, null, order);
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

    public ArrayList<String> retrievePizzaListViewRows() {// return an ArrayList instead of String

        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[]{"id", "type", "description", "price"};
        Cursor cursor = db.query(DB_TABLE_PIZZAS, columns, null, null, null, null, null);

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

    public ArrayList<String> retrieveOrdersListViewRows() {// return an ArrayList instead of String

        ArrayList<String> productRows = new ArrayList<String>();

        String[] columns = new String[]{"orderId", "userEmail", "userAddress", "totalPrice", "item1", "item1Qty", "item1", "item2", "item2Qty", "item3", "item3Qty", "item4", "item4Qty", "item5", "item5Qty", "item6", "item6Qty"};
        //String whereClaus = "userEmail ="+email;
        Cursor cursor = db.query(DB_TABLE_ORDERS, columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            productRows.add(cursor.getString(4)+" x"+cursor.getString(5));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }

    public ArrayList<String> retrieveSidesListViewRows() {// return an ArrayList instead of String

        ArrayList<String> productRows = new ArrayList<String>();

        String[] columns = new String[]{"id", "type", "description", "price"};

        Cursor cursor = db.query(DB_TABLE_SIDES, columns, null, null, null, null, null);

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

    public String retrieveSides() {
        String[] columns = new String[]{"id", "type", "description", "price"};

        String whereClaus = "id == 1";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE_SIDES, columns, whereClaus, null, null, null, order);
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



    //********************************************************************
    //****************       UN-USED METHODS       ***********************
    //********************************************************************

    /*


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
    public String retrievePizzas() {

        String[] columns = new String[]{"id", "product_name", "product_description", "price"};

        String whereClaus = "id >= 103";
        String order = "id";

        Cursor cursor = db.query(DB_TABLE, columns, whereClaus, null, null, null, order);

        String tablerows = "";
        return tablerows;
    }

    */

}