package com.example.medremainder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class Home extends AppCompatActivity {

    Button btn;
    private int requestCode;
    private String[] permissions;
    private int[] grantResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn=findViewById(R.id.sentmail);

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if(ContextCompat.checkSelfPermission(Home.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
            if(dayOfWeek==6){


                sendMessage();
            }
            else {
                ActivityCompat.requestPermissions(Home.this,new String[]{Manifest.permission.SEND_SMS},100);
            }
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Report sented on "+dayOfWeek, Toast.LENGTH_SHORT).show();
            }
        });
        


    }

    private void sendMessage() {
        String num="9207641733";
        String msg="patiend didnt take med";
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(num,null,msg,null,null);

        Toast.makeText(Home.this, "message delivered is "+msg+" on ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.grantResults = grantResults;
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==10 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }
    }
}