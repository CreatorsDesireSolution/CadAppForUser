package com.example.cadappforuser.SqliteDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Myhelper extends SQLiteOpenHelper {
    private  static  final  String dbname="CartDb";
    private  static  final int version =1;
    public Myhelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //price real
        String sql="CREATE TABLE CART(_id INTEGER PRIMARY KEY AUTOINCREMENT,SERVICE_NAME TEXT,SERVICE_DESC TEXT,SERVICE_PRICE TEXT,SERVICE_IMAGE TEXT,QTY INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {

        }
    }
    /*public void delete(int position) {

        SQLiteDatabase db = this.getWritableDatabase();
        String table = "PRODUCT";
        String whereClause = "id";
        String [] whereArgs = new String[] {String.valueOf(position)};
        db.delete (table, whereClause, whereArgs);

    }*/
}
