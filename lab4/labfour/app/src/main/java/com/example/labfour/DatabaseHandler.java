package com.example.labfour;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    // Toàn bộ các biến tĩnh
    // Phiên bản Database
    private static final int DATABASE_VERSION = 1;

    // Tên Database
    private static final String DATABASE_NAME = "contactsManager";

    // Tên bảng "Contacts"
    private static final String TABLE_CONTACTS = "contacts";

    // Các trường trong bảng "Contacts"
    private static final String KEY_ID    = "id";
    private static final String KEY_NAME  = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Khi có sự kiện cập nhật database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng "Contacts" (nếu đã tồn tại)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Khởi tạo lại bảng "Contacts"
        onCreate(db);
    }

    // Thêm mới contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase(); // Mở kết nối cho phép ghi vào db

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number

        // Chèn bản ghi vào db
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Đóng kết nối database
    }

    // Lấy contact theo id
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        else
            return null;
        // return contact
        return new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
    }

    // Lấy toàn bộ contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Lặp để lấy ra toàn bộ dữ liệu và thêm nó vào list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Thêm contact vào list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Cập nhật contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // cập nhật dữ liệu
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
    }

    // Xóa bản ghi contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    public int getContactId(String contactName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int contactId = -1; // Default value if contact is not found

        String[] columns = {KEY_ID};
        String selection = KEY_NAME + "=?";
        String[] selectionArgs = {contactName};

        Cursor cursor = db.query(TABLE_CONTACTS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(KEY_ID);

            if (idIndex != -1) {
                contactId = cursor.getInt(idIndex);
            }

            cursor.close();
        }

        return contactId;
    }


    public void deleteContactid(int contactId) {
            SQLiteDatabase db = this.getWritableDatabase();

            // Xác định điều kiện để xóa contact dựa trên ID
            String whereClause = KEY_ID + "=?";
            String[] whereArgs = {String.valueOf(contactId)};

            // Thực hiện lệnh xóa
            db.delete(TABLE_CONTACTS, whereClause, whereArgs);

            db.close();
        }
    public List<String> getAllContactNames(List<Contact> contacts) {
        List<String> contactNames = new ArrayList<>();

        for (Contact contact : contacts) {
            String name = contact.getName();
            contactNames.add(name);
        }

        return contactNames;
    }
}