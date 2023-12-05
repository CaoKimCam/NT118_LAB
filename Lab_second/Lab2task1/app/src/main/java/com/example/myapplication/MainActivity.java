package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> myadapter;
    ArrayList<String> danhsach = new ArrayList<>();
    Button btnSubmit;
    EditText etName;
    TextView tvselection;
    ListView lvPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit=(Button) findViewById(R.id.btn_Submit);
        etName=(EditText) findViewById(R.id.et_Name);
        lvPerson = (ListView) findViewById(R.id.lv_Person);
        tvselection =(TextView) findViewById(R.id.tvSelection);

        danhsach.add("Teo");
        danhsach.add("Ty");
        danhsach.add("Bin");
        danhsach.add("Bo");
        myadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, danhsach);
        lvPerson.setAdapter(myadapter);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String textt=etName.getText().toString();
                if(!TextUtils.isEmpty(textt))
                {
                    danhsach.add(textt);
                    myadapter.notifyDataSetChanged();
                }
            }
        });
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                String value=lvPerson.getItemAtPosition(arg2).toString();
                tvselection.setText("position: "+arg2+"; value ="+ value);
                tvselection.setBackgroundColor(getResources().getColor(R.color.blue));
                //int color=res.
                //tvselection.setBackgroundColor(getCol);
            }
        });
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                if (TextUtils.isEmpty(myadapter.getItem(position))) return false;
                danhsach.remove(position);
                myadapter.notifyDataSetChanged();
                return false;
            }
        });


    }
}