package com.example.sms13033;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StageTwoFragment extends Fragment {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private View view;
    private int code_num = -1;
    private String text;

    public StageTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_stage_two, container, false);


        radioGroup = view.findViewById(R.id.StageTwoFragment_radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = view.findViewById(checkedId);

                code_num = Integer.parseInt(radioButton.getTag().toString());

                text = radioButton.getText().toString();
            }
        });
        return view;
    }


    public String toString() {
        return "StageTwoFragment";
    }

    public Boolean Check() {

        if (code_num != -1) {
            return true;
        } else {
            Toast.makeText(getActivity(), "Παρακαλώ επιβλέψτε ένα από τα παραπάνω.",
                    Toast.LENGTH_SHORT).show();
            return false;

        }

    }

    public int getCode_num() {
        return code_num;
    }

    public String getMessage() {
        return text;
    }
}
