package com.example.sms13033;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private ProgressBar progressBar;
    private StageOneFragment stageOneFragment;
    private StageTwoFragment stageTwoFragment;
    private Button button_next, btn;
    private FragmentManager fragmentManager;
    private Fragment fra;
    private SmsManager smsManager;
    private String phoneNumber = "000";
    private String final_Message;
    private GlobalClass globalClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalClass = (GlobalClass) getApplicationContext();

        stageOneFragment = new StageOneFragment();
        stageTwoFragment = new StageTwoFragment();

        progressBar = findViewById(R.id.progressBar);
        button_next = findViewById(R.id.button_next_stage);
       // btn = findViewById(R.id.button);
        fragmentManager = getSupportFragmentManager();

        /*String name = globalClass.getFirst_name();
        String last = globalClass.getLast_name();
        String ad = globalClass.getAddress();*/
        // Log.i("globalClass", "globalClass:" + globalClass.getAddress());
        if (globalClass.isOk()) {
            /*Toast.makeText(getApplicationContext(), "name : " + name+
                            "\nlast : " + last
                            + "\nAddress :" + ad,
                    Toast.LENGTH_LONG).show();*/
            fra = stageTwoFragment;
            button_next.setText("Αποστολή");
            progressBar.setProgress(80);
        } else {
            fra = stageOneFragment;
        }
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fra).commit();
        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (fra.toString().equals("StageOneFragment") && stageOneFragment.Check()) {
                    fra = stageTwoFragment;
                    button_next.setText("Αποστολή");
                    progressBar.setProgress(80);
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fragment_container, fra).commit();
                } else if (fra.toString().equals("StageTwoFragment") && stageTwoFragment.Check()) {
                    sendSMSMessage();

                }
            }
        });
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUserInfo(stageOneFragment.getLast_name(), stageOneFragment.getLast_name(), stageOneFragment.getAddress());
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        if (fra.toString().equals("StageTwoFragment")) {
            fra = stageOneFragment;
            button_next.setText("Επόμενο");
            progressBar.setProgress(50);
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.fragment_container, fra).commit();
        }
    }

    protected void sendSMSMessage() {
        // Log.i("MainActivity", "sendSMSMessage");

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {

            Send();
        }
    }


    public void Send() {
        String info = stageOneFragment.getInfo();
        String id = stageTwoFragment.getCode_num() + "";
        final_Message = id + " " + info.toUpperCase();
        SaveUserInfo(stageOneFragment.getLast_name(), stageOneFragment.getLast_name(), stageOneFragment.getAddress());
        //Log.i("MainActivity", "Permission has already been granted");
        smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, final_Message, null, null);
        Toast.makeText(getApplicationContext(), "Το SMS στάλθηκε με επιτυχία.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.i("MainActivity", "onRequestPermissionsResult");

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Send();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Κάτι πήγε στραβά ξανά παρακαλώ προσθέστε.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }


    public void SaveUserInfo(String first_name, String last_name, String address) {
        String final_str = last_name + "\n" + first_name + "\n" + address;
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(getString(R.string.USER_FILE_INFO), MODE_PRIVATE);
            fos.write(final_str.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
