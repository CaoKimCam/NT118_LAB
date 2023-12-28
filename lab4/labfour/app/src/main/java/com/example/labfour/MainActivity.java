package com.example.labfour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayAdapter<String> adapter;
    DatabaseHandler db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact= findViewById(R.id.lv_Contact);

        db = new DatabaseHandler(this);

        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedContactName = (String) parent.getItemAtPosition(position);

                // Xác định ID của contact dựa trên tên (hoặc bạn có thể lấy ID khác)
                int contactId = db.getContactId(selectedContactName);

                // Xóa contact từ database
                db.deleteContactid(contactId);

                // Cập nhật ListView
                updateListView();

                Toast.makeText(MainActivity.this, "Contact deleted", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        //

        updateListView();

        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }
    }

    private void updateListView() {
        List<Contact> contacts = db.getAllContacts();
        List<String> contactNames = db.getAllContactNames(contacts);
        if(adapter== null)
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        else
        {
            adapter.clear();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        }
        adapter.notifyDataSetChanged();
        lvContact.setAdapter(adapter);
    }
}