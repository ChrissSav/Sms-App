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

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private ProgressBar progressBar;
    private StageOneFragment stageOneFragment;
    private StageTwoFragment stageTwoFragment;
    private Button button_next;
    private FragmentManager fragmentManager;
    private Fragment fra;
    private SmsManager smsManager;
    private String phoneNumber = "000";
    private String final_Message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stageOneFragment = new StageOneFragment();
        stageTwoFragment = new StageTwoFragment();
        progressBar = findViewById(R.id.progressBar);
        button_next = findViewById(R.id.button_next_stage);
        fragmentManager = getSupportFragmentManager();
        fra = stageOneFragment;
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fra).commit();

        button_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (fra.toString().equals("StageOneFragment") && stageOneFragment.Check()) {
                    fra = stageTwoFragment;
                    progressBar.setProgress(100);
                    fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left).replace(R.id.fragment_container, fra).commit();
                } else if (fra.toString().equals("StageTwoFragment") && stageTwoFragment.Check()) {
                    sendSMSMessage();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (fra.toString().equals("StageTwoFragment")) {
            fra = stageOneFragment;
            progressBar.setProgress(50);
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.fragment_container, fra).commit();
        }
    }

    protected void sendSMSMessage() {
       // Log.i("MainActivity", "sendSMSMessage");

        String info = stageOneFragment.getInfo();
        String id = stageTwoFragment.getCode_num() + "";
        final_Message = id + " " + info.toUpperCase();


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
}
