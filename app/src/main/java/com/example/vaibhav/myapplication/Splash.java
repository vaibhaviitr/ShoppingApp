package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {

    MediaPlayer ourSong;
    protected void onCreate(Bundle TravisLoveBacon){
        super.onCreate(TravisLoveBacon);
        setContentView(R.layout.splash);
        ourSong = MediaPlayer.create(Splash.this,R.raw.splashsound);
        ourSong.start();
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                     e.printStackTrace();
                }finally{
                    Intent openStartingName = new Intent("com.example.vaibhav.myapplication.MENU");
                    startActivity(openStartingName);
                }
            }
        };
        timer.start();
    }
    protected void onPause(){
        super.onPause();
        ourSong.release();
        finish();
    }
}
