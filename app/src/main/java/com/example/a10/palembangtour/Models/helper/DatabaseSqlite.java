package com.example.a10.palembangtour.Models.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.a10.palembangtour.Models.Result;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSqlite extends SQLiteOpenHelper {

    private static final String TAGS = DatabaseSqlite.class.getSimpleName();

    private static final String Tags = DatabaseSqlite.class.getSimpleName();

    public DatabaseSqlite(Context context) {
        super(context, Constant.DATABASE.DB_NAME, null, Constant.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constant.DATABASE.CREATE_TABLE_QUERY);
        }catch (SQLiteException ex){
            Log.d(Tags, ex.getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constant.DATABASE.DROP_QUERY);
        this.onCreate(db);
    }

    // ----- CRUD ------

    public void addData(Result result){

        Log.d(TAGS, "Value Got " + result.getNama());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Constant.DATABASE.ID, result.getId());
        value.put(Constant.DATABASE.NAMA, result.getNama());
        value.put(Constant.DATABASE.DAYA_TARIK, result.getDayaTarik());
        value.put(Constant.DATABASE.LOKASI, result.getLokasi());
        value.put(Constant.DATABASE.FASILITAS, result.getFasilitas());
        value.put(Constant.DATABASE.PENGELOLA, result.getPengelola());
        value.put(Constant.DATABASE.JARAK_TEMPUH, result.getJarakTempuh());
        value.put(Constant.DATABASE.IMAGE_URL, result.getImage());
        value.put(Constant.DATABASE.IMAGE, Utils.getPictureByteOfArray(result.getPicture()));


        try {
            db.insert(Constant.DATABASE.TABLE_NAME, null, value);
        }catch (Exception e){

        }
        db.close();
    }

    public List<Result> getDataList(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(Constant.DATABASE.GET_DATA_QUERY, null);

        List<Result> dataList = new ArrayList<>();

        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {

                    Result listData = new Result();
                    listData.setNama(cursor.getColumnName(cursor.getColumnIndex(Constant.DATABASE.NAMA)));

                    listData.setPicture(Utils.getBitmapFromByte(cursor.getBlob(cursor.getColumnIndex(Constant.DATABASE.IMAGE))));

                    dataList.add(listData);
                }while (cursor.moveToNext());
            }
        }

        return dataList;
    }
}
