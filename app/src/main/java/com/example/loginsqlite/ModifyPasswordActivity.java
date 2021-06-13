package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyPasswordActivity extends AppCompatActivity {

    EditText newPass;
    Button bttnmodifpass;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifpass);
        newPass = (EditText) findViewById(R.id.newpassword);
        bttnmodifpass = (Button) findViewById(R.id.bttnmodifpass);
        db = new DBHelper(this);

        bttnmodifpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = newPass.getText().toString();

                if (pass.equals(""))
                    Toast.makeText(ModifyPasswordActivity.this, "Please fill-in everything.", Toast.LENGTH_SHORT).show();
                else {
                        Intent intent1 = getIntent();
                        String old = intent1.getStringExtra(LoginActivity.EXTRA_NAME);
                        Boolean update = db.updatePassword(pass,old);
                        if (update) {
                            Toast.makeText(ModifyPasswordActivity.this, "Successfully changed the password!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ModifyPasswordActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ModifyPasswordActivity.this, "Change failed.", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

    }
}