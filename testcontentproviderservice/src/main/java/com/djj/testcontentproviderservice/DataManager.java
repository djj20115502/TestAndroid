package com.djj.testcontentproviderservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DongJunJie on 2016-8-24.
 */
public class DataManager {


    class DBHelper extends SQLiteOpenHelper {

        private final static String DB_NAME = "TestDB";

        private final static String DB_TABLE_NAME = "test_table";
        private final static String DB_CREAT_SQL = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_NAME
                + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name VARCHAR FOREIGN KEY,"
                + "level INTEGER)";

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory
                factory, int
                                version) {
            super(context, name, factory, version);
        }

        public DBHelper(Context context) {
            this(context, DB_NAME, null, 0);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREAT_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }
}
