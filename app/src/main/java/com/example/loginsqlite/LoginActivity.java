package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button bttnlogin;
    DBHelper db;
    public static final String EXTRA_NAME = "com.example.loginsqlite.EXTRA_TEXT";
    public static final String EXTRA_PASS = "com.example.loginsqlite.EXTRA_PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        bttnlogin = (Button) findViewById(R.id.bttnlogin1);
        db = new DBHelper(this);

        bttnlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please fill-in everything.", Toast.LENGTH_SHORT).show();

                else{
                    Boolean checkuserpass = db.checkusernamepassword(user,pass);

                    if(checkuserpass){
                        Toast.makeText(LoginActivity.this, "Success on login.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        //intent.putExtra("Uniqid","From_Login");
                        intent.putExtra(EXTRA_NAME, username.getText().toString());
                        intent.putExtra(EXTRA_PASS, password.getText().toString());
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}