package com.example.cadappforuser.SqliteDatabase;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.util.Log;

public class FavDB extends SQLiteOpenHelper {

    private static int DB_VERSION= 1;
    private static String DATABASE_NAME= "Favorite";
    private static String TABLE_NAME= "favoriteTable";
    public static String KEY_ID= "id";
    public static String SERVICE_NAME= "serviceName";
    public static String SERVICE_IMAGE= "serviceImage";
    public static String SERVICE_PRICE= "servicePrice";
    public static String SERVICE_DESC= "serviceDesc";


    public static String FAVORITE_STATUS="fStatus";

    //

    private static String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT," + SERVICE_NAME + " TEXT,"
            + SERVICE_IMAGE + " TEXT,"+SERVICE_PRICE + " TEXT,"+SERVICE_DESC+ " TEXT," + FAVORITE_STATUS + " TEXT)";

    public FavDB(Context context){
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //create empty table

    public  void insertEmpty(){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        for(int i=1;i<11;i++){
            cv.put(KEY_ID,i);
            cv.put(FAVORITE_STATUS,"0");

            db.insert(TABLE_NAME,null,cv);
        }
    }

    //insert data into database
    public void insertIntoDatabase(String item_name,String item_price,String item_desc,int item_image,String id,String fav_status){
        SQLiteDatabase db;

        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(SERVICE_NAME,item_name);
        cv.put(SERVICE_PRICE,item_price);
        cv.put(SERVICE_DESC,item_desc);

        cv.put(SERVICE_IMAGE,item_image);
        cv.put(KEY_ID,id);
        cv.put(FAVORITE_STATUS,fav_status);
        db.insert(TABLE_NAME,null,cv);

        Log.d("FavDB Status",item_name + ", favstatus- "+fav_status+"-. "+cv);
    }

    //read all data
    public Cursor real_all_data(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select * from " + TABLE_NAME + " where " + KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }

    //remove line from database
    public void remove_fav(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="UPDATE "+ TABLE_NAME +" SET "+ FAVORITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
    }

    //select all favorite list

    public Cursor select_all_favorite_list(){
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }

}
