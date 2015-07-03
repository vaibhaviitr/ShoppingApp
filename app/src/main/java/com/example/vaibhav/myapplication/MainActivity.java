package com.example.vaibhav.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.io.FileInputStream;


public class MainActivity extends SlidingFragmentActivity implements MainFragment.Onmennu,MainFragment.Onslide,
        Runnable,RandomList.OnLogin, RandomList.OnNotification{
    SlidingMenu sm;
    private Fragment mContent;
    public static String mob_no,name,encryptedpassword,points;
    public static final String FILENAME = "user";
    FileInputStream fis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("super", "true");
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Log.e("features", "true");
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_frame);
        Log.e("setcontents", "true");
        try {
            fis = openFileInput(FILENAME);
            Log.e("openfileinput", "true");
        }catch (Exception e){
            e.printStackTrace();
        }
        new RandomList().bmp = BitmapFactory.decodeStream(fis);
        Log.e("setrandomlistbitmap", "true");
        mContent = new MainFragment();
        Log.e("set mcontent", "true");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, new RandomList()).commit();
        Log.e("inflaterandomlist", "true");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mContent).commit();
        Log.e("inflate mcontent", "true");

    }
    @Override
    public void mennuevent() {
        initializeSlidingmenu();
        if(!new MainFragment().bool) {
            Thread t = new Thread(this);
            t.start();
        }
    }
    @Override
    public void slideevent() {
        sm.toggle();
    }

    public void switchContent(Fragment fragment){
        mContent = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        getSlidingMenu().showContent();
    }
    public void initializeSlidingmenu(){

        sm = getSlidingMenu();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            sm.setBehindOffset(100);
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            sm.setBehindOffset(300);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        mContent.onDestroy();
        new MainFragment().bool = true;
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainFragment()).commit();
        if(getSharedPreferences("user", MODE_MULTI_PROCESS) != null){
            mob_no = getSharedPreferences("user", MODE_MULTI_PROCESS).getString("mob_no", "");
            encryptedpassword = getSharedPreferences("user", MODE_MULTI_PROCESS).getString("encpass", "");
            name = getSharedPreferences("user", MODE_MULTI_PROCESS).getString("name", "");
            points = getSharedPreferences("user", MODE_MULTI_PROCESS).getString("points", "");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "User Logged in", Toast.LENGTH_SHORT);
                }
            });

        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT);
                }
            });

        }
    }

    @Override
    public void loginevent() {
        Slide slider = new Slide();
        getWindow().setExitTransition(slider);
        Intent lola = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(lola, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
    }

    @Override
    public void notificationevent() {
        Slide slider = new Slide();
        getWindow().setExitTransition(slider);
        Intent lola = new Intent(MainActivity.this, Notifications.class);
        startActivity(lola, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
    }
}
