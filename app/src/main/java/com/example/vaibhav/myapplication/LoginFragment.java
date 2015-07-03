package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tamoghna on 09-06-2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    public static String name;
    TextView tvname,forgot,enter,donthaveaccnt;
    EditText mobno,password;
    OnSignup msignup;
    OnLogin mlogin;
    public interface OnSignup{
        public void signupevent();
    }
    public interface  OnLogin{
        public void loginevent();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            msignup = (OnSignup)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            mlogin = (OnLogin)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login, container, false);
        initialize(root);
        tvname.setText("Welcome " + name);
        return root;
    }
    private void initialize(View view){
        tvname = (TextView) view.findViewById(R.id.tvloginname);
        forgot = (TextView) view.findViewById(R.id.loginforgot);
        enter = (TextView) view.findViewById(R.id.tvloginenter);
        donthaveaccnt = (TextView) view.findViewById(R.id.tvdonthaveanaccount);
        mobno = (EditText) view.findViewById(R.id.etloginmobileno);
        password = (EditText) view.findViewById(R.id.etloginpassword);
        forgot.setOnClickListener(this);
        enter.setOnClickListener(this);
        donthaveaccnt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginforgot:
                msignup.signupevent();
                break;
            case R.id.tvloginenter:
                Toast.makeText(getActivity(),"User Logged In", Toast.LENGTH_SHORT).show();
                mlogin.loginevent();
                break;
            case R.id.tvdonthaveanaccount:
                msignup.signupevent();
                break;
        }
    }
}
