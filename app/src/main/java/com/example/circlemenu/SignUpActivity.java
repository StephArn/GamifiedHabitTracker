package com.example.circlemenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    //refactor, am redenumit clasa pentru a nu exista conflicte

    EditText username, password, repassword;
    Button signup, login;
    DBHelper db;
    public static final String EXTRA_NAME = "com.example.loginsqlite.EXTRA_NAME";
    public static final String EXTRA_PASS = "com.example.loginsqlite.EXTRA_PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //refactor - schimbat si denumirea fisierului de layout pt a nu exista conflicte
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.username);
        password =  (EditText) findViewById(R.id.password);
        repassword =  (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.bttnsignup);
        login = (Button) findViewById(R.id.bttnlogin);
        db = DBHelper.getInstance(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("") )
                    Toast.makeText(SignUpActivity.this, "Please fill-in everything.", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = db.checkusername(user);
                        if(!checkuser){
                            Boolean insert = db.insertUser(user,pass);
                            if(insert){
                                Toast.makeText(SignUpActivity.this, "Succes on registration!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                intent.putExtra("Uniqid","From_SignUp");
//                                intent.putExtra(EXTRA_NAME, username.getText().toString());
//                                intent.putExtra(EXTRA_PASS, password.getText().toString());
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUpActivity.this,"Failed to register.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUpActivity.this,"This user already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this,"Passwords do not match.", Toast.LENGTH_SHORT).show();
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