package com.example.labfour;

public class Contact {

    // Các biến private
    int _id;
    String _name;
    String _phone_number;

    // Một phương thức khởi tạo rỗng
    public Contact(){

    }
    // Phương thức khởi tạo với đầy đủ các params
    public Contact(int id, String name, String _phone_number){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }

    // Phương thức khởi tạo chỉ có thông tin cho name và phone_number
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }
    // lấy giá trị id
    public int getID(){
        return this._id;
    }

    // set giá trị id
    public void setID(int id){
        this._id = id;
    }

    // lấy giá trị name
    public String getName(){
        return this._name;
    }

    // set giá trị name
    public void setName(String name){
        this._name = name;
    }

    // lấy giá trị phone_number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // set giá trị phone_number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}