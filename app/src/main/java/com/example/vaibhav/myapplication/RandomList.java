package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileInputStream;

/**
 * Created by Tamoghna on 08-06-2015.
 */
public class RandomList extends Fragment implements View.OnClickListener{
    public static Bitmap bmp;
    CircularImageView image;
    TextView profilename, profilepoints, home, myorders, notifications, login, settings;
    OnLogin mlogin;
    OnNotification mnotification;
    OnSettings msettings;
    public interface OnLogin{
        public void loginevent();
    }
    public interface OnNotification{
        public void notificationevent();
    }
    public interface OnSettings{
        public void settingsevent();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mlogin = (OnLogin)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            mnotification = (OnNotification)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            mnotification = (OnNotification)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View menuview = inflater.inflate(R.layout.menu,container,false);
        initialize(menuview);
        if(bmp != null) {
            image.setImageBitmap(bmp);
        }else{
            image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.user1));
        }
        if(new MainActivity().name != null) {
            profilename.setText(new MainActivity().name);
        }
        if(new MainActivity().points != null) {
            profilepoints.setText(new MainActivity().points + " Points");
        }
        return menuview;
    }
    private void initialize(View view) {
        profilename = (TextView) view.findViewById(R.id.profilename);
        profilepoints = (TextView) view.findViewById(R.id.profilepoints);
        home = (TextView) view.findViewById(R.id.home);
        myorders = (TextView) view.findViewById(R.id.myorders);
        notifications = (TextView) view.findViewById(R.id.notifications);
        login = (TextView) view.findViewById(R.id.login);
        settings = (TextView) view.findViewById(R.id.settings);
        image = (CircularImageView) view.findViewById(R.id.profileview);
        home.setOnClickListener(this);
        myorders.setOnClickListener(this);
        notifications.setOnClickListener(this);
        login.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:

                break;
            case R.id.myorders:

                break;
            case R.id.notifications:
                Log.e("kubh", "habibi");
                mnotification.notificationevent();
                break;
            case R.id.login:
                mlogin.loginevent();
                break;
            case R.id.settings:
                msettings.settingsevent();
                break;
        }
    }
}
