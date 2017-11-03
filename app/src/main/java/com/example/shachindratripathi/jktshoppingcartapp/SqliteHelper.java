package com.example.shachindratripathi.jktshoppingcartapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shachindratripathi on 16/10/17.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    SQLiteDatabase dbWriteableObj = this.getWritableDatabase();

    public static final String DATABASE_NAME = "Shopping.db";
    public static final String TABLE_NAME = "ItemsList.db";
    public static final String BUCKET_TABLE_NAME = "Bucket.db";
    public static final String COL_1 = "ITEM_CODE ";
    public static final String COL_2 = "ITEM_NAME";
    public static final String COL_3 = "PRICE";
    public static final String COL_4 = "QUANTITY";
    public static final String CREATE_TABLE = "create table" + TABLE_NAME + " " +
            "(_ITEM_CODE VARCHAR PRIMARY KEY AUTOINCREMENT, ITEM_NAME "
            + "VARCHAR " + "PRICE TEXT,QUANTITY TEXT);";



    public SqliteHelper(Context context) {
        super(context, "Items.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
           dbWriteableObj.execSQL(CREATE_TABLE);
        }
        catch (SQLException e){
            
        }
        try {
            dbWriteableObj.execSQL("create table" + BUCKET_TABLE_NAME + " (" + COL_1 + "TEXT PRIMARY KEY AUTOINCREMENT, ITEM_NAME TEXT, " +
                     "PRICE TEXT,QUANTITY TEXT)");
        } catch (SQLException e) {
          //  Toast.makeText(this,"" ,Toast.LENGTH_LONG ).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          dbWriteableObj.execSQL("drop table if exists" +  TABLE_NAME);
          dbWriteableObj.execSQL("drop table if exists" +  BUCKET_TABLE_NAME);
          onCreate(dbWriteableObj);

    }
    public boolean insertData (String code,String name,String rate,String quantity) {

        SQLiteDatabase dbWriteableObj = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("COL_1",code);
        values.put("COL_2",name);
        values.put("COL_3",rate);
        values.put("COL_4",quantity);
        long result = dbWriteableObj.insert(TABLE_NAME,null,values);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public void insertCartData( String code) {


        String whereArgs[] = new String[]{code};
        String query = null;
        try {
            query = "insert into BUCKET_TABLE_NAME select * from TABLE_NAME WHERE ITEM_CODE = ?";
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbWriteableObj.execSQL(query,whereArgs);

    }

    public Cursor displayAllItems(){
         SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
         Cursor cursorObj = dbWriteableObj.rawQuery("select * from " + TABLE_NAME,   null );
         return cursorObj;
    }

    public Cursor searchItems(String code){
        // SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
        String whereArgs[] = new String[]{code};
        String query = "select from " + TABLE_NAME + "where ITEM_CODE = ? ";
        Cursor cursorObj =  dbWriteableObj.rawQuery(query,whereArgs);
        return cursorObj;
    }

    public Cursor search(String code){
        // SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
        String whereArgs[] = new String[]{code};
        String query = "select from " + TABLE_NAME + "where ITEM_CODE = ? ";
        Cursor cursorObj =  dbWriteableObj.rawQuery(query,whereArgs);
        return cursorObj;
    }

    public Cursor searchCart(String code){
        // SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
        String whereArgs[] = new String[]{code};
        String query = "select from " + BUCKET_TABLE_NAME + "where ITEM_CODE = ? ";
        Cursor cursorObj =  dbWriteableObj.rawQuery(query,whereArgs);
        return cursorObj;
    }

    public Cursor displayCartItems(){
         SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
         Cursor cursorObj = dbWriteableObj.rawQuery("select * from " + BUCKET_TABLE_NAME,   null );
         return cursorObj;
    }

    public int updateData(String code,String quantity){
        SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereArgs[] = new String[]{code};
        values.put(COL_4,quantity);
        int count =  dbWriteableObj.update(TABLE_NAME, values, "code = ?" ,whereArgs);
        return count;

    }

    public int updateCartData(String code,String quantity){
        SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereArgs[] = new String[]{code};
        values.put(COL_4,quantity);
        int count =  dbWriteableObj.update(BUCKET_TABLE_NAME, values, "code = ?" ,whereArgs);
        return count;

    }

    public boolean delete(String code){
        SQLiteDatabase dbWriteableObj = this.getWritableDatabase();
        String whereArgs[] = new String[]{code};
         dbWriteableObj.delete(TABLE_NAME,"code=?",whereArgs);
        return true;
    }
  /* public boolean checkTable()
    {
       // dbWriteableObj.execSQL("SELECT * FROM Items.db WHERE name ='TABLE_NAME' and type='table'");
        Cursor cursor = null;
        String sql ="SELECT * FROM "+TABLE_NAME +" WHERE CODE=?";
        cursor= dbWriteableObj.rawQuery(sql,null);
        Log("Cursor Count : " + cursor.getCount());

        if(cursor.getCount()>0){
            //PID Found
        }else{
            //PID Not Found
        }
        cursor.close();

    }   */



}
