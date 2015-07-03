package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Tamoghna on 07-06-2015.
 */
public class MainFragment extends Fragment {
    private TransitionManager mTransitionManager;
    private Scene mScene1;
    private Scene mScene2;
    Onmennu mennu;
    Onslide slide;
    public static boolean bool=false;

    public interface Onslide{
        public void slideevent();
    }

    public interface Onmennu{
        public void mennuevent();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mennu = (Onmennu)activity;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        try{
            slide = (Onslide)activity;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public MainFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("booooool", String.valueOf(bool));
        if(!bool) {
            View rootView = inflater.inflate(R.layout.lol, container, false);
            TransitionInflater transitionInflater = TransitionInflater.from(getActivity());
            mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_manager, container);
            mScene1 = Scene.getSceneForLayout(container, R.layout.lol, getActivity());
            mScene2 = Scene.getSceneForLayout(container, R.layout.lol2, getActivity());
            ((RelativeLayout) rootView.findViewById(R.id.relativelayoutmiddle)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTransitionManager.transitionTo(mScene2);
                    mennu.mennuevent();
                }
            });
            return rootView;
        }else {
            mennu.mennuevent();
            View secondview = inflater.inflate(R.layout.lol2, container, false);
            ((ImageButton) secondview.findViewById(R.id.menubutton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("mom", "mom");
                    slide.slideevent();
                }
            });
            return secondview;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
