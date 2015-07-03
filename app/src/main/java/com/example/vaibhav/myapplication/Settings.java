package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

/**
 * Created by Tamoghna on 11-06-2015.
 */
public class Settings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.settings);
    }
}
