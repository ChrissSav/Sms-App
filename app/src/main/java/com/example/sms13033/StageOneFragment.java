package com.example.sms13033;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StageOneFragment extends Fragment {


    private EditText first_name;
    private EditText last_name;
    private EditText address;
    private View view;
    private GlobalClass globalClass;

    public StageOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stage_one, container, false);
        globalClass = (GlobalClass)getActivity().getApplicationContext();
        first_name = view.findViewById(R.id.StageOneFragment_editText_first_name);
        last_name = view.findViewById(R.id.StageOneFragment_editText_last_name);
        address = view.findViewById(R.id.StageOneFragment_editText_address);

        first_name.setText(globalClass.getFirst_name());
        last_name.setText(globalClass.getLast_name());
        address.setText(globalClass.getAddress());
        return view;
    }

    public String toString() {
        return "StageOneFragment";
    }

    public Boolean Check() {

        /*Log.i("StageOneFragment", first_name.getText().toString());
        Log.i("StageOneFragment", last_name.getText().toString());
        Log.i("StageOneFragment", address.getText().toString());*/

        if(first_name.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Το πεδίο του ονόματος είναι κενό !",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(last_name.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Το πεδίο του επωνύμου είναι κενό !",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(address.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Το πεδίο της διεύθυνση κατοικίας είναι κενό !",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    public String getInfo() {
        return last_name.getText().toString() + " " + first_name.getText().toString().toUpperCase() + "\n" + address.getText().toString().toUpperCase();
    }

    public String getFirst_name() {
        return first_name.getText().toString();
    }


    public String getLast_name() {
        return last_name.getText().toString();
    }



    public String getAddress() {
        return address.getText().toString();
    }


}