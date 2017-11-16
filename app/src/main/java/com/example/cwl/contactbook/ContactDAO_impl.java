package com.example.cwl.contactbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by CWL on 2017/11/16.
 */

public class ContactDAO_impl implements ContactDAO {
    public MySQLiteHelper helper;

    public ContactDAO_impl(Context context)
    {
        helper = new MySQLiteHelper(context);
    }

    @Override
    public void add(Contact c) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", c.name);
        cv.put("tel", c.tel);
        cv.put("addr", c.addr);

        db.insert("contact", null, cv);

        db.close();
    }

    @Override
    public void delete(Contact c) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("contact", "id=?", new String[]{String.valueOf(c.id)});
        db.close();
    }

    @Override
    public void edit(Contact c) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", c.name);
        cv.put("tel", c.tel);
        cv.put("addr", c.addr);

        db.update("contact", cv, "id=?", new String[]{String.valueOf(c.id)});

        db.close();
    }

    @Override
    public Contact[] getList() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("contact", new String[]{"id", "name", "tel", "addr"}, null, null, null, null, null);

        ArrayList<Contact> arrayList = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                Contact c = new Contact();
                c.id = cursor.getInt(0);
                c.name = cursor.getString(1);
                c.tel = cursor.getString(2);
                c.addr = cursor.getString(3);
                arrayList.add(c);
            } while (cursor.moveToNext());
        }

        Contact returnValue[] = arrayList.toArray(new Contact[arrayList.size()]);

        return returnValue;
    }

    @Override
    public Contact getOne(int id) {
        Contact c = new Contact();

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query("contact", new String[]{"id", "name", "tel", "addr"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        c.id = cursor.getInt(0);
        c.name = cursor.getString(1);
        c.tel = cursor.getString(2);
        c.addr = cursor.getString(3);

        db.close();

        return c;
    }
}
