package com.example.myapplicationtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lvPerson;
    TextView tvSelection;
    ArrayList<String> mang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPerson=(ListView) findViewById(R.id.lv_Person);
        tvSelection=(TextView) findViewById(R.id.tvSelection);
        mang = new ArrayList<String>();

        mang.add("Teo");
        mang.add("Ty");
        mang.add("Bin");
        mang.add("Bo");

        ArrayAdapter adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, mang
        );


        lvPerson.setAdapter(adapter);
        lvPerson.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        tvSelection.setText("position: "+ arg2 +"; value =" + mang.get(arg2));
                    }
                }
        );
    }
}