package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyUsernameActivity extends AppCompatActivity {

    EditText newUsername;
    Button bttnmodif;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifuser);
        newUsername = (EditText) findViewById(R.id.newusername);
        bttnmodif = (Button) findViewById(R.id.bttnmodifusername);
        db = new DBHelper(this);

        bttnmodif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = newUsername.getText().toString();

                if (user.equals(""))
                    Toast.makeText(ModifyUsernameActivity.this, "Please fill-in everything.", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = db.checkusername(user);
                    if (!checkuser) {
                        Intent intent1 = getIntent();
                        String old = intent1.getStringExtra(LoginActivity.EXTRA_NAME);
                        Boolean update = db.updateUsername(old, user);
                        if (update) {
                            Toast.makeText(ModifyUsernameActivity.this, "Successfully changed the username!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ModifyUsernameActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ModifyUsernameActivity.this, "Change failed.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ModifyUsernameActivity.this, "This user already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}