package com.example.diyetkalori;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Foods.db", null, 1);
    }

    public DBHelper(UrunFragment urun) {
        super(urun.getContext(), "Foods.db", null, 1);
    }

    public DBHelper(DietFragment diet) {
        super(diet.getContext(), "Foods.db", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Foods(foodname TEXT primary key, calories TEXT, carbs TEXT, protein TEXT, fat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists Foods");

    //dikkat onCreate(db);
    }



    public Boolean insertfoodsdata(String foodname, String calories, String carbs, String protein, String fat)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //İçeriye eklenecek ürünleri seçme
        contentValues.put("foodname", foodname);
        contentValues.put("calories", calories);
        contentValues.put("carbs", carbs);
        contentValues.put("protein", protein);
        contentValues.put("fat", fat);
        long result= db.insert("Foods", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    /*public Boolean deletealldata(String foodname) {
        while (!foodname.equals("null")) {
            this.getWritableDatabase().delete("Foods", "Foodname *", null);
            return true;
        }
        return false;
    }*/



    public Boolean updatefoodsdata(String foodname, String calories, String carbs, String protein, String fat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("calories", calories);
        contentValues.put("carbs", carbs);
        contentValues.put("protein", protein);
        contentValues.put("fat", fat);
        Cursor cursor = db.rawQuery("Select * from Foods where foodname = ?", new String[]{foodname});
        if (cursor.getCount() > 0) {


            long result = db.update("Foods", contentValues, "foodname=?", new String[]{foodname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public void deletefoodsdata (String foodname)
    {
        this.getWritableDatabase().delete("Foods", "foodname ='"+ foodname+"'",null);
    }






    /* public Boolean deletefoodsdata(String foodname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Foods where foodname = ?", new String[]{foodname});
        if (cursor.getCount() > 0) {
            long result = db.delete("Foods",  "name=?", new String[]{foodname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    } */



    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from foods ",null);
        return cursor;
    }

    public Cursor getDataFoodnames(String foodnames)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from foods where foodname = '"+foodnames+"'",null);
        return cursor;
    }
}
