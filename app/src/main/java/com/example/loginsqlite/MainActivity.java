package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password =  (EditText) findViewById(R.id.password);
        repassword =  (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.bttnsignup);
        login = (Button) findViewById(R.id.bttnlogin);
        db = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("") )
                    Toast.makeText(MainActivity.this, "Please fill-in everything.", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = db.checkusername(user);
                        if(!checkuser){
                            Boolean insert = db.insertUser(user,pass);
                            if(insert){
                                Toast.makeText(MainActivity.this, "Succes on registration!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Failed to register.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"This user already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}