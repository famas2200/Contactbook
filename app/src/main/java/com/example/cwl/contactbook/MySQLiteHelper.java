package com.example.cwl.contactbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CWL on 2017/11/16.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "contact.sqlite";
    public static final int VERSION = 2;

    public MySQLiteHelper(Context context) {
        super(context, DB_Name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE \"contact\" (\"id\" INTEGER PRIMARY KEY  NOT NULL ,\"name\" VARCHAR,\"tel\" VARCHAR,\"addr\" VARCHAR DEFAULT (null) )";
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2)
        {
            String upgradeSQL = "ALTER TABLE \"main\".\"contact\" ADD COLUMN \"email\" VARCHAR";
            db.execSQL(upgradeSQL);
        }
    }
}
