package com.example.cadappforuser.SqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;
import java.util.Set;

 public class MyTable
{
    private String TABLE_NAME = "my_table";
    int DATABASE_VERSION = 1;
    private String _ID = "id";
    private String _CHK_VALUES = "checkbox_value";

    public String getTableName(){           return TABLE_NAME;}
    public int getDatabaseVersion() {       return DATABASE_VERSION;}

    public String getID()       {       return _ID;         }
    public String getScore()    {       return _CHK_VALUES;     }

    public String getDatabaseCreateQuery()
    {
        final String DATABASE_CREATE =
                "create table IF NOT EXISTS " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, "
                        + _CHK_VALUES + " TEXT NOT NULL)";

        return DATABASE_CREATE;
    }
}

