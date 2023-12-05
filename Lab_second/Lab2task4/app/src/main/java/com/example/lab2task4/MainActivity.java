package com.example.lab2task4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etFullName;
    CheckBox cbManager;
    Button btnAdd;
    ListView lvEmployee;
    ArrayList<Employee> employee;
    EmployeeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFullName =(EditText) findViewById(R.id.et_FullName);
        cbManager=(CheckBox) findViewById(R.id.iv_Manager);
        btnAdd=(Button) findViewById(R.id.btn_Add);
        lvEmployee= (ListView) findViewById(R.id.lv_Employee);
        employee = new ArrayList<Employee>();

        adapter = new EmployeeAdapter(this, R.layout.item_employee, employee);
        lvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etFullName.getText().toString();
                Employee employee1 = new Employee();
                if (cbManager.isChecked())
                {
                    employee1.setManager(true);
                }
                else
                {
                    employee1.setManager(false);
                }
                employee1.setFullName(name);
                employee.add(employee1);
                adapter.notifyDataSetChanged();
            }
        });
    }

}