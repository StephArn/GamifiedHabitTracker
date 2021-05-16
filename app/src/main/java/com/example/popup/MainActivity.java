package com.example.popup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayPopup(View view)
    {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rootLayout),R.string.offline_massage,Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}