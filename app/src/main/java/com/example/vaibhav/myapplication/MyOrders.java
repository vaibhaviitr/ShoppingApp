package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;
import android.widget.ExpandableListView;

import org.json.JSONArray;

/**
 * Created by Tamoghna on 12-06-2015.
 */
public class MyOrders extends Activity {
    JSONArray arr;
    ExpandableListView expand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.myorders);
        arr = new JSONArray();
        expand = (ExpandableListView) findViewById(R.id.expandableListView);
        expand.setAdapter(new ExpandableListAdapter(this,arr));
    }
}
