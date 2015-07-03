package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Tamoghna on 10-06-2015.
 */
public class Notifications extends Activity implements View.OnClickListener{
    boolean notificationstate = false;
    TextView noti,non,noff;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.notifications);
        getWindow().setEnterTransition(new Explode());
        initialize();
    }
    public void initialize(){
        noti = (TextView) findViewById(R.id.tvnotifications);
        non = (TextView) findViewById(R.id.onbutton);
        noff = (TextView) findViewById(R.id.offbutto);
        non.setOnClickListener(this);
        noff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.onbutton:
                if(!notificationstate){
                    notificationstate = true;
                    non.setBackground(getResources().getDrawable(R.color.blue));
                    non.setTextColor(Color.WHITE);
                    noff.setBackground(null);
                    noff.setTextColor(Color.BLACK);
                    noti.setText("Turn Off Notifications");
                }
                break;
            case R.id.offbutto:
                if(notificationstate){
                    notificationstate = false;
                    noff.setBackground(getResources().getDrawable(R.color.blue));
                    noff.setTextColor(Color.WHITE);
                    non.setBackground(null);
                    non.setTextColor(Color.BLACK);
                    noti.setText("Turn On Notifications");
                }
                break;
        }
    }
}
