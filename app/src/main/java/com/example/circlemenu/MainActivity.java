package com.example.circlemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;
import com.google.android.material.snackbar.Snackbar;
import com.ramotion.circlemenu.CircleMenuView;

public class MainActivity extends AppCompatActivity
{
    String[] message = {"Hi! I'm Marcel, your assistant in becoming a better person!",
            "Hello there! My name is Marcel and I am here to make sure you don't give up!",
            "Hello! I am Marcel, your virtual friend! Let's get this started!"};
    Random random = new Random();
    int select = random.nextInt(message.length);
    View screenView;
    int[] color;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Atentionare
        Intent intent = getIntent();
        String text = intent.getStringExtra(calend.EXTRA_DATE);
        TextView textView1 = (TextView) findViewById(R.id.textview1);
        textView1.setText(text);

        // Culoare fundal
        color = new int[] {Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.YELLOW, Color.CYAN};
        screenView =  findViewById(R.id.rView);

        // Mesajele de incurajare
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, message[select], Snackbar.LENGTH_LONG)
                .setAction("CLOSE", view -> {})
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .show();

        // Meniu rotita
        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener()
        {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view)
            {
                Log.d("D","onMenuOpenAnimationStart");
            }
            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view)
            {
                Log.d("D","onMenuOpenAnimationEnd");
            }
            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view)
            {
                Log.d("D","onMenuCloseAnimationStart");
            }
            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view)
            {
                Log.d("D","onMenuCloseAnimationEnd");
            }
            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index)
            {
                Log.d("D","onButtonClickAnimationStart|index: "+index);
            }
            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index)
            {
                Log.d("D","onButtonClickAnimationEnd|index: "+index);

                if (index == 0)
                {
                    openCalend();
                }

                if (index == 1)
                {
                    niceMessage();
                }

//                if (index == 2)
//                {
//                    // TODO
//                }

                if (index == 3)
                {
                    int aryLength = color.length;

                    Random random = new Random();
                    int rNum = random.nextInt(aryLength);

                    screenView.setBackgroundColor(color[rNum]);
                }

                if (index == 4)
                {
                    openProfile();
                }
            }
            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int buttonIndex)
            {
                Log.d("D","onButtonLongClick|index: "+buttonIndex);
                return true;
            }
            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int buttonIndex)
            {
                Log.d("D","onButtonLongClickAnimationStart|index: "+buttonIndex);
            }
            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int buttonIndex)
            {
                Log.d("D","onButtonLongClickAnimationEnd|index: "+buttonIndex);
            }
        });
    }

    public void niceMessage()
    {
        String[] message1 = {"You can do this!", "It's a good day to make changes!",
                "Don't give up on your goals!", "It's never to late be become a better person!"};
        Random random1 = new Random();
        int select1 = random1.nextInt(message1.length);

        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage(message1[select1])
                .setPositiveButton("Continue", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                })
                .create();
        myAlert.show();
    }

    public void openCalend()
    {
        Intent intent = new Intent(this, calend.class);
        startActivity(intent);
    }

    public void openProfile()
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}