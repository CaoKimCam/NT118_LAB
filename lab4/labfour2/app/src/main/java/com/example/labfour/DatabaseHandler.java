package com.example.labfour;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="contactsManager";
    private static final String TABLE_CONTACTS ="contacts";
    private static final String KEY_ID ="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO="phone_number";
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE ="CREATE TABLE "+
                TABLE_CONTACTS+"("
                +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+
                " TEXT, "+KEY_PH_NO+" TEXT"+");";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override bn
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
    }
    //Them 1 contact
    public void addContact (Contact contact){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
    //Lay 1 contact
    public Contact getContact(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                KEY_NAME, KEY_PH_NO}, KEY_NAME + "=?" ,
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;
        return new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

    }
    //Lay toan bo Contact
    public List<Contact> getAllContact(){
        List<Contact> contactList = new ArrayList<>();
        //select all query
        String selectQuey = "SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery(selectQuey, null);
        if (cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
    //Update contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // cập nhật dữ liệu
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }
    public void deleteContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_NAME + " = ?", new String[]{String.valueOf(name)});
        db.close();
    }
}



