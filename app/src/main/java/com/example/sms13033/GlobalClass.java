package com.example.sms13033;

import android.app.Application;
import android.util.Log;

public class GlobalClass extends Application {

    private String Address;
    private String Last_name;
    private String First_name;

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getFull_name() {
        return First_name + " " + Last_name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Boolean isOk() {
        Boolean add = Address != null;
        Boolean last = Last_name != null;
        Boolean first = First_name != null;

        //Log.i("Address ", re.toString());
          return first && last && add;
    }
}
