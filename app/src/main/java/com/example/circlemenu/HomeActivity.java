package com.example.circlemenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    Button modifUser, modifPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        modifUser = (Button) findViewById(R.id.bttnmodifusername);
        modifPass = (Button) findViewById(R.id.bttnmodifpass);
        //Intent intent = getIntent();
        //String strdata = intent.getExtras().getString("Uniqid");

        modifUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(strdata.equals("From_SignUp"))
//                {
//                    Intent intent1 = new Intent(getApplicationContext(), ModifyUsernameActivity.class);
//                    intent1.putExtra("Uniqid","From_SignUp");
//                    String name = intent1.getStringExtra(SignUpActivity.EXTRA_NAME);
//                    String pass = intent1.getStringExtra(SignUpActivity.EXTRA_PASS);
//                    intent1.putExtra(SignUpActivity.EXTRA_NAME, name);
//                    intent1.putExtra(SignUpActivity.EXTRA_PASS, pass);
//                    startActivity(intent1);
//
//
//                }
//                else if(strdata.equals("From_Login"))
//                {
                Intent intent2 = getIntent();
                Intent intent1 = new Intent(getApplicationContext(), ModifyUsernameActivity.class);
                String name = intent2.getStringExtra(LoginActivity.EXTRA_NAME);
                //String pass = intent1.getStringExtra(LoginActivity.EXTRA_PASS);
                intent1.putExtra(LoginActivity.EXTRA_NAME, name);
                startActivity(intent1);
                //intent1.putExtra(LoginActivity.EXTRA_PASS, pass);
                //}
            }
        });
        modifPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(strdata.equals("From_SignUp"))
//                {
//                    Intent intent1 = new Intent(getApplicationContext(), ModifyPasswordActivity.class);
//                    intent1.putExtra("Uniqid","From_SignUp");
//                    String name = intent1.getStringExtra(SignUpActivity.EXTRA_NAME);
//                    String pass = intent1.getStringExtra(SignUpActivity.EXTRA_PASS);
//                    intent1.putExtra(SignUpActivity.EXTRA_NAME, name);
//                    intent1.putExtra(SignUpActivity.EXTRA_PASS, pass);
//                    startActivity(intent1);
//
//
//                }
//                else if(strdata.equals("From_Login"))
//                {
                Intent intent2 = getIntent();
                Intent intent1 = new Intent(getApplicationContext(), ModifyPasswordActivity.class);
                String name = intent2.getStringExtra(LoginActivity.EXTRA_NAME);
                String pass = intent2.getStringExtra(LoginActivity.EXTRA_PASS);
                intent1.putExtra(LoginActivity.EXTRA_NAME, name);
                intent1.putExtra(LoginActivity.EXTRA_PASS, pass);
                startActivity(intent1);
                //}
            }
        });


    }
}