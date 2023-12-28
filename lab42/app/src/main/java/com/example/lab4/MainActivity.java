package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtmalop, edttenlop, edtsiso;
    Button btnadd, btndelete, btnupdate, btnquery;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtmalop=findViewById(R.id.edt_malop);
        edttenlop=findViewById(R.id.edt_tenlop);
        edtsiso=findViewById(R.id.edt_siso);
        btnadd=findViewById(R.id.btn_add);
        btndelete=findViewById(R.id.btn_delete);
        btnupdate=findViewById(R.id.btn_update);
        btnquery=findViewById(R.id.btn_query);

        lv =findViewById(R.id.lv_student);
        arrayList= new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(myadapter);
        //Tạo và mở cơ sở dử liệu
        mydatabase = openOrCreateDatabase("QLSV.db", MODE_PRIVATE, null);
        //Tạo Table chứa dữ liệu
        try {
            String sql ="CREATE TABLE tbllop(malop TEXT primary key,tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        }
        catch (Exception e)
        {
            Log.e("Error","Table đã tồn tại");
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = mydatabase.rawQuery("SELECT * FROM "+"tbllop", null);
                cursor.moveToPosition(position);
                edtmalop.setText(cursor.getString(0));
                edttenlop.setText(cursor.getString(1));
                edtsiso.setText(cursor.getString(2));
                cursor.close();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtmalop.getText().toString();
                String tenlop= edttenlop.getText().toString();
                int siso= Integer.parseInt(edtsiso.getText().toString());

                ContentValues myvalue= new ContentValues();
                myvalue.put("malop", malop);
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);
                String msg="";
                if(mydatabase.insert("tbllop", null, myvalue) == -1)
                {
                    msg = "FAIL TO INSERT RECORD!";
                } else{
                    msg="Thêm thành công!";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop=edtmalop.getText().toString();
                int n= mydatabase.delete("tbllop", "malop = ?",new String[]{malop});
                String msg="";
                if(n==0){
                    msg="No record to Update";
                }else
                    msg="Xóa thành công!";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int siso= Integer.parseInt(edtsiso.getText().toString());
                String malop=edtmalop.getText().toString();
                ContentValues myvalue = new ContentValues();
                myvalue.put("siso", siso);
                int n= mydatabase.update("tbllop", myvalue, "malop = ?", new String[]{malop});
                String msg="";
                if(n == 0){
                    msg = "No record to Update";
                }
                else msg = "Update thành công!";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                Cursor c = mydatabase.query("tbllop", null,null,null,null,null, null);
                c.moveToNext();
                String data="";
                while (c.isAfterLast() == false)
                {
                    data=c.getString(0)+ " - "+c.getString(1)+" - "+c.getString(2);
                    c.moveToNext();
                    arrayList.add(data);
                }
                c.close();
                myadapter.notifyDataSetChanged();
            }
        });
    }
}