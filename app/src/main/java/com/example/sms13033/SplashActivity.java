package com.example.sms13033;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME = 2000;
    private GlobalClass globalClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        globalClass = (GlobalClass) getApplicationContext();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                Load();
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }

        }, SPLASH_TIME);
    }

    public void Load() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(getString(R.string.USER_FILE_INFO));
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            int i = 0;
            while ((text = br.readLine()) != null) {
               // Log.i("Load_Info1", "text:" + text + " i = " + i);
                sb.append(text).append("\n");
                if (i == 0) {
                    globalClass.setLast_name(text);
                } else if (i == 1) {
                    globalClass.setFirst_name(text);
                } else if (i == 2) {
                    globalClass.setAddress(text);
                }
                i++;
            }
            //Log.i("Load_Info", "\ntext:" + sb.toString() + "\n" + sb);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
