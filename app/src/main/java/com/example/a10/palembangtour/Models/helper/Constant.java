package com.example.a10.palembangtour.Models.helper;

/**
 * Created by 10 on 03/07/2017.
 */

public class Constant {



    public static final class HTTP{

        public static final String BASE_URL = "https://palembangtours.000webhostapp.com/";
        //public static final String BASE_URL = "http://localhost/db/";
    }

    public static final class DATABASE{

        public static final String DB_NAME = "db_data";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "wstAlam";


        public static final String DROP_QUERY = "DROP TABLE IF EXIST " + TABLE_NAME;

        public static final String GET_DATA_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String ID = "id";
        public static final String NAMA = "nama";
        public static final String DAYA_TARIK = "dayaTarik";
        public static final String LOKASI = "lokasi";
        public static final String FASILITAS = "fasilitas";
        public static final String PENGELOLA = "pengelola";
        public static final String JARAK_TEMPUH = "jarakTempuh";
        public static final String IMAGE_URL = "image_url";
        public static final String IMAGE = "image";

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE" + TABLE_NAME + "" +
                "(" + ID + "VARCHAR PRIMARY KEY ," +
                NAMA + "TEXT not null ," +
                DAYA_TARIK + " TEXT not null ," +
                LOKASI + " TEXT not null ," +
                FASILITAS + " TEXT not null ," +
                PENGELOLA + " TEXT not null ," +
                JARAK_TEMPUH + " TEXT not null ," +
                IMAGE_URL + " TEXT not null ," +
                IMAGE + " blob not null ," ;

    }


    public static final class REFERENCE{
        public static final String RESULT = Config.PACKAGE_NAME + "result";
    }

    public static final class Config{
        public static final String PACKAGE_NAME = "com.example.a10.palembangtour.";
    }

}
