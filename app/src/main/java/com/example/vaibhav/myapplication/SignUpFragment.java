package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tamoghna on 09-06-2015.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener{
    int i;
    String mobnom;
    TextView already, enter;
    EditText mobno;
    Onbacklogin mlogin;
    OnPassword mpassword;
    public interface Onbacklogin{
        public void backloginevent();
    }
    public interface OnPassword{
        public void passwordevent(String mobnom);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mlogin = (Onbacklogin)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            mpassword = (OnPassword)activity;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("oncreateview", "success");
        View root = inflater.inflate(R.layout.entermobno,container,false);
        i=0;
        initialize(root);
        return root;
    }
    public void initialize(View view){
        already = (TextView) view.findViewById(R.id.alreadyhaveaccnt);
        enter = (TextView) view.findViewById(R.id.tvsignupenter);
        mobno = (EditText) view.findViewById(R.id.etsignup);
        already.setOnClickListener(this);
        enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.alreadyhaveaccnt:
                if(i==0) {
                    mlogin.backloginevent();
                }
                break;
            case R.id.tvsignupenter:
                Log.e("enterevent", "successful");
                if(i==0){
                    Log.e("mobnocheck", "success");
                    String regx = "[0-9]+";
                    if(mobno.getText().toString().matches(regx) && mobno.getText().toString().length()==10){
                        i++;
                        mobnom = mobno.getText().toString();
                        mobno.setHint("Enter OTP");
                        mobno.setText("");
                        already.setText("Enter your OTP received on your mobile number");
                        break;
                    }else{
                        Toast.makeText(getActivity(),"enter a valid 10 digits mobile number", Toast.LENGTH_SHORT).show();
                    }
                }
                if(i==1){
                    Log.e("otpcheck", "success");
                    mpassword.passwordevent(mobnom);
                }
                break;
        }
    }
}
