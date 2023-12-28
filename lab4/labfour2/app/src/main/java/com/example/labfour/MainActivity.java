package com.example.labfour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvContact;
    ArrayList<Contact> listContacts;
    ContactAdapter myadapter;
    DatabaseHandler db;
    EditText edt_name, edt_phonenumber;
    Button btn_them, btn_xoa, btn_sua, btn_capnhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_name=findViewById(R.id.et_name);
        edt_phonenumber=findViewById(R.id.et_phonenumber);
        btn_them=findViewById(R.id.btn_add);
        btn_xoa=findViewById(R.id.btn_delete);
        btn_sua=findViewById(R.id.btn_edit);
        btn_capnhat=findViewById(R.id.btn_load);


        listContacts = new ArrayList<>();
        myadapter = new ContactAdapter(this, R.layout.item_contact, listContacts);
        rcvContact=findViewById(R.id.rcv_contact);
        rcvContact.setLayoutManager(new LinearLayoutManager(this));
        rcvContact.setAdapter(myadapter);
        db = new DatabaseHandler(this);

        btn_them.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                String phonenumber = edt_phonenumber.getText().toString();
                Contact contact= new Contact(name, phonenumber);
                db.addContact(contact);

            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= edt_name.getText().toString();
                String msg;
                if(myadapter.getItemCount()==0){
                    msg="No record to Delete";
                }
                else {
                    msg=name+" is deleted.";
                    db.deleteContact(name);
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListView();
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                contact.setName(edt_name.getText().toString());
                contact.setPhoneNumber(edt_phonenumber.getText().toString());

            }
        });
    }


    private void updateListView() {
        ArrayList<Contact> contacts= (ArrayList<Contact>) db.getAllContact();
        if(myadapter == null)
            myadapter = new ContactAdapter(this, android.R.layout.simple_list_item_1, contacts);
        else {
            myadapter = new ContactAdapter(this, android.R.layout.simple_list_item_1, contacts);
        }
        myadapter.notifyDataSetChanged();
        rcvContact.setAdapter(myadapter);
    }
}