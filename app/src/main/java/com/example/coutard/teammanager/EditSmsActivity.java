package com.example.coutard.teammanager;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by COUTARD on 09/01/2018.
 */

public class EditSmsActivity extends AppCompatActivity {


    TextView textView;
    EditText editTextMessage;
    Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);


        editTextMessage = (EditText) findViewById(R.id.messageSms);

        sendButton = (Button) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTeam();
                finish();
            }
        });




        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("SmsSender", "Permission not Accorded");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
            sendButton.setEnabled(false);
        } else {
            Log.d("SmsSender", "Permission accorded");


        }


    }

    public ArrayList<String> getPhoneNumbers(){
        if (getIntent().hasExtra("team")) {

            Team team = getIntent().getParcelableExtra("team");
            ArrayList<String> teamNumbers = new ArrayList<String>() ;
            for (int i = 0; i < team.getTeam().size(); i ++  ){
                if (!team.getTeam().get(i).getPlayerPhone().equals("")) {
                    teamNumbers.add(team.getTeam().get(i).getPlayerPhone());
                }
               // Log.i("debug",teamNumbers.toString() );
            }

            return teamNumbers;
        }else{
            System.out.println("Sorry but no team has bean chosen");
            return null;
        }

    }






    private void sendTeam() {
        for (int i =0; i < getPhoneNumbers().size();i++){
            SmsManager smsManager = SmsManager.getDefault();
            Log.i("debug",getPhoneNumbers().get(i) );
            if (!editTextMessage.getText().toString().equals("")) {
                smsManager.sendTextMessage(
                        getPhoneNumbers().get(i),
                        null,
                        editTextMessage.getText().toString(),
                        null,
                        null);
                Log.i("smsSender","sms sent to : " +getPhoneNumbers().get(i));
            } else {
                Toast.makeText(getApplicationContext(), "Nothing to SEND!",
                        Toast.LENGTH_LONG).show();
            }

            //textView.setText("SMS sent");
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        }

    }


/*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("SmsSender", "Permission has been granted");
                sendButton.setEnabled(true);
            } else {
                Log.d("SmsSender", "Permission has been denied or request cancelled");
                textView.setText("You can not send SMS!");
                sendButton.setEnabled(false);
            }

        }
    }
    */
}

