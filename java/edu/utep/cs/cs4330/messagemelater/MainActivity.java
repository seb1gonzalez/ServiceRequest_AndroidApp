package edu.utep.cs.cs4330.messagemelater;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phoneText, msgText,delayText;
    Button send;

    private static final int REQUEST_SMS_PERMISSION = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showToast("Please fill all values");
        setContentView(R.layout.activity_main);
        phoneText = findViewById(R.id.phoneEdit);
        msgText = findViewById(R.id.msgEdit);
        delayText = findViewById(R.id.delayEdit);
        send = findViewById(R.id.sendButton);

        requestSmsPermission();
        send.setOnClickListener(e -> sendPressed());
    }

    private void sendPressed(){


        Intent intent = new Intent(getApplicationContext(), MessageIntentService.class);
        String message = msgText.getText().toString();
        String phoneNumber = phoneText.getText().toString();
       int delay =Integer.parseInt(delayText.getText().toString());

       if(message.isEmpty()){
           showToast("Enter a message");
       }
     else if(phoneNumber.isEmpty()){
          showToast("Enter a phone number");

      }
      else if (delay == 0){
           showToast("Enter a delay greater than 0");
      }


       else {
           intent.putExtra("phone", phoneNumber);
           intent.putExtra("delay", delay);
           intent.putExtra("msg", message);
           showToast("Got it!");
           startService(intent);
       }



    }
    private void requestSmsPermission() {
        String smsPermission = Manifest.permission.SEND_SMS;
        int grant = ContextCompat.checkSelfPermission(this, smsPermission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[] { smsPermission };
            ActivityCompat.requestPermissions(this, permissions, REQUEST_SMS_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            showToast(grantResults[0] == PackageManager.PERMISSION_GRANTED ?
                    "Permission granted!" : "Permission not granted!");
        }
    }
    private void showToast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
}



