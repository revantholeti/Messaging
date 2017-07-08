package revanth.messaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import static revanth.messaging.R.id.login;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2;
    CheckBox ch;
    Button b1;
    Intent in;
    SharedPreferences sp;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailkey";
    public static final String Password = "passkey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ch=(CheckBox)findViewById(R.id.check);
        ed1=(EditText)findViewById(R.id.email);
        ed2=(EditText)findViewById(R.id.pass);

        b1=(Button)findViewById(login);
        sp=getSharedPreferences("login",MODE_PRIVATE);
        if(sp.contains("username") && sp.contains("password")){
            startActivity(new Intent(MainActivity.this, text.class));
            finish();   //finish current activity


        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=ed1.getText().toString();
                String pass=ed2.getText().toString();
                if(email.length()!=0 && pass.length()!=0) {
                    logincheck();
                }
                else{
                    if(email.length() == 0){
                        Toast.makeText(getApplicationContext(),"Invalid Mail",Toast.LENGTH_LONG).show();
                    }
                    else if(pass.length() == 0){
                        Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(!isChecked){
                    ed2.setInputType(129);
                }
                else{
                    ed2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }


    void logincheck(){
        //check username and password are correct and then add them to SharedPreferences

            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();

            startActivity(new Intent(MainActivity.this,text.class));
            finish();

    }
}
