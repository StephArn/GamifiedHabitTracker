package com.example.circlemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Random;
import com.example.circlemenu.R;
import com.google.android.material.snackbar.Snackbar;
import com.ramotion.circlemenu.CircleMenuView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    String[] message = {"You can do this!", "It's o good day to make changes!", "Don't give up on your goals!", "It's never to late be become a better person!"};
    Random random = new Random();
    int select = random.nextInt(message.length);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, message[select], Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {}
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .show();

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener(){
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
}