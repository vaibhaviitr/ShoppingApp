package com.example.vaibhav.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tamoghna on 10-06-2015.
 */
public class PasswordFragment extends Fragment {
    EditText password, confirm;
    TextView enter;
    OnPasswordSet mpasswordset;
    public interface OnPasswordSet{
        public void passwordsetevent(String password);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mpasswordset = (OnPasswordSet)activity;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.password,container,false);
        initialize(root);
        return root;
    }
    public void initialize(View view){
        password = (EditText) view.findViewById(R.id.etpassword);
        confirm = (EditText) view.findViewById(R.id.etconfirmpassword);
        enter = (TextView) view.findViewById(R.id.tventer);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().contentEquals(confirm.getText().toString())){
                    mpasswordset.passwordsetevent(password.getText().toString());

                }else{
                    Toast.makeText(getActivity(),"Passwords Doesn't Match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
