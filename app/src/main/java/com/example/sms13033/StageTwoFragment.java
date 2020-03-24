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
                switch (checkedId) {
                    case R.id.StageTwoFragment_radio_b1:
                        code_num = 1;
                        break;
                    case R.id.StageTwoFragment_radio_b2:
                        code_num = 2;
                        break;
                    case R.id.StageTwoFragment_radio_b3:
                        code_num = 3;
                        break;
                    case R.id.StageTwoFragment_radio_b4:
                        code_num = 4;
                        break;
                    case R.id.StageTwoFragment_radio_b5:
                        code_num = 5;
                        break;
                    case R.id.StageTwoFragment_radio_b6:
                        code_num = 6;
                        break;

                }
                // Log.i("StageTwoFragment", "checkedId " + radioButton.getid);
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
