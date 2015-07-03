package com.example.vaibhav.myapplication;



import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tamoghna on 09-06-2015.
 */
public class LoginActivity extends SlidingFragmentActivity implements LoginFragment.OnSignup,LoginFragment.OnLogin,
        SignUpFragment.Onbacklogin, SignUpFragment.OnPassword, PasswordFragment.OnPasswordSet{
    SlidingMenu sm;
    FragmentManager manage;
    FragmentTransaction ft;
    String mobno, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Explode slider = new Explode();
        getWindow().setEnterTransition(slider);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, new RandomList()).commit();
        sm = getSlidingMenu();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            sm.setBehindOffset(100);
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            sm.setBehindOffset(300);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        manage = getSupportFragmentManager();
        ft = manage.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.container, new LoginFragment(), "LOGIN");
        ft.addToBackStack(null);
        ft.commit();
        new LoginFragment().name = getSharedPreferences("user", MODE_MULTI_PROCESS).getString("name", "");
    }

    @Override
    public void signupevent() {
        ft = manage.beginTransaction();
        ft.replace(R.id.container, new SignUpFragment(), "SIGNUP");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void loginevent() {
        getWindow().setExitTransition(new Explode());
        Intent lola = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(lola, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
    }

    @Override
    public void passwordevent(String mobno) {
        this.mobno = mobno;
        ft = manage.beginTransaction();
        ft.replace(R.id.container, new PasswordFragment(), "PASSWORD");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void backloginevent() {
        ft = manage.beginTransaction();
        ft.replace(R.id.container, new LoginFragment(), "LOGIN");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    public void passwordsetevent(String password) {
        Encrypt encrypt = new Encrypt();
        try{
            this.password = encrypt.encrypt(password);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            String suu =  encrypt.decrypt(this.password);
            Log.e("decrypt", suu);
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] haha = {this.mobno, this.password};
        new Signuptask(LoginActivity.this).execute(haha);
        Toast.makeText(this, "User Signed Up", Toast.LENGTH_SHORT);
        getWindow().setExitTransition(new Explode());
        Intent lola = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(lola, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
    }
    public class Signuptask extends AsyncTask<String,Void,Void>{

        private ProgressDialog dialog;
        private Activity activity;
        private Context context;
        public Signuptask(Activity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void s) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        @Override
        protected Void doInBackground(String... params) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", "jon snow");
                jsonObject.put("password", params.clone()[1]);
                jsonObject.put("mobileno", params.clone()[0]);
                jsonObject.put("email", "jonsnow@dead.com");
            }catch (JSONException e){
                e.printStackTrace();
            }
            String uri = "localhost:80/signup";
            String url = "http://192.168.0.119:80/signup";
            JSONPost post = new JSONPost();
            JSONArray response = post.postJSON(jsonObject,uri);
            Log.e("json", response.toString());
            return null;
        }
    }
}
