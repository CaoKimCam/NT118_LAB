package com.example.lab2task3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText editId,editName;
    Button btnNhap;
    RadioGroup radgroup;
    ListView lvNhanvien;
    ArrayList<Employee>arrEmployee=new ArrayList<Employee>();
    ArrayAdapter<Employee>adapter=null;
    //Khai báo 1 employee object
    Employee employee=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editId=(EditText) findViewById(R.id.et_MaNV);
        editName=(EditText) findViewById(R.id.et_TenNV);
        btnNhap=(Button) findViewById(R.id.btn_NhapNV);
        radgroup=(RadioGroup) findViewById(R.id.radiogroud1);
        lvNhanvien=(ListView) findViewById(R.id.lv_NhanVien);
//đưa Data Source là các employee vào Adapter
        adapter=new ArrayAdapter<Employee>(this,
                android.R.layout.simple_list_item_1,
                arrEmployee);
//đưa adapter vào ListView
        lvNhanvien.setAdapter(adapter);

        btnNhap.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
// TODO Auto-generated method stub
                processNhap();
            }
        });
    }
    //Xử lý sự kiện nhập
    public void processNhap()
    {
//Lấy ra đúng id của Radio Button được checked
        int radId=radgroup.getCheckedRadioButtonId();
        String id=editId.getText()+"";
        String name=editName.getText()+"";
        if(radId==R.id.rd_ChinhThuc)
        {
//tạo instance là FullTime
            employee=new EmployeeFullTime();
        }
        else
        {
//Tạo instance là Partime
            employee=new EmployeePartTime();
        }
//FullTime hay Partime thì cũng là Employee
//nên có các hàm này là hiển nhiên
        employee.setId(id);
        employee.setName(name);
//Đưa employee vào ArrayList
        arrEmployee.add(employee);
//Cập nhập giao diện
        adapter.notifyDataSetChanged();
    }
}