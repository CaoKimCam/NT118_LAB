package com.example.lab2task4;

public class Employee {
    private String ID;
    private String FullName;
    private boolean isManager;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
    public boolean isManager ()
    {
        return isManager;
    }
    public void setManager (boolean manager)
    {
        isManager=manager;
    }
    //if this is a manger
}
