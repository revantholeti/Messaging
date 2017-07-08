package revanth.messaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static revanth.messaging.R.layout.textactivity;

/**
 * Created by Revanth on 05-07-2017.
 */

public class text extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    private static final int RESULT_LOAD_IMAGE = 1;
    Button sendBtn, logBtn,imgselbtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;
    Bitmap img;
    ImageView image;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(textactivity);

        sendBtn = (Button) findViewById(R.id.sendbtn);
        txtphoneNo = (EditText) findViewById(R.id.phntext);
        txtMessage = (EditText) findViewById(R.id.messagetext);
        logBtn = (Button) findViewById(R.id.logout);
        imgselbtn = (Button) findViewById(R.id.selimg);
        image = (ImageView) findViewById(R.id.img);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMSMessage();
            }
        });

        imgselbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.clear();
                e.commit();

                startActivity(new Intent(text.this,MainActivity.class));
                finish();   //finish current activity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            selectedImage = data.getData();
            image.setImageURI(selectedImage);
        }
    }

    private void sendSMSMessage(){
        String toPhoneNumber = txtphoneNo.getText().toString();
        String smsMessage = txtMessage.getText().toString();
        if(toPhoneNumber.length()==0){
            txtphoneNo.setError("Phone number is required!");
        }
        if(toPhoneNumber.length()==10) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(toPhoneNumber, null, smsMessage, null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Sending SMS failed.",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter valid num",Toast.LENGTH_LONG).show();
        }
    }
}
