package com.example.harry.mad_assignment1_harrisonjenkins_17967352;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends AppCompatActivity {


SendSMS mSender = new SendSMS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        boolean success = mSender.sendSMSMessage("0409122199",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, … est laborum.");
        Toast.makeText(this, "Message sent " + (success ? "successfully" : "unsuccessfully"),
                Toast.LENGTH_SHORT).show();

    }


    //online example https://www.mkyong.com/android/how-to-send-sms-message-in-android/

    /************
     @Override
     public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_send_sms);

     buttonSend = (Button) findViewById(R.id.buttonSend);
     textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
     textSMS = (EditText) findViewById(R.id.editTextSMS);

     buttonSend.setOnClickListener(new View.OnClickListener() {

     @Override
     public void onClick(View v) {

     String phoneNo = textPhoneNo.getText().toString();
     String sms = textSMS.getText().toString();

     try {
     SmsManager smsManager = SmsManager.getDefault();
     smsManager.sendTextMessage(phoneNo, null, sms, null, null);
     Toast.makeText(getApplicationContext(), "SMS Sent!",
     Toast.LENGTH_LONG).show();
     } catch (Exception e) {
     Toast.makeText(getApplicationContext(),
     "SMS faild, please try again later!",
     Toast.LENGTH_LONG).show();
     e.printStackTrace();
     }

     }
     });
     }
     *************/


}
