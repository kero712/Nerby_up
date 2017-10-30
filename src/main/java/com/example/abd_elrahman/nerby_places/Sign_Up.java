package com.example.abd_elrahman.nerby_places;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sign_Up extends Fragment implements View.OnClickListener{
    FragmentManager fragmentManager;
    private View view;

    EditText email,user_name,password;
    Button sign_up;
    TextView birthDate ,log_in;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.sign__up,container,false);


        UI();


        return view;
    }
    void UI(){
        fragmentManager = getFragmentManager();
        user_name = (EditText)view.findViewById(R.id.text_sign_up_name);
        email = (EditText)view.findViewById(R.id.text_sign_up_email);
        password = (EditText)view.findViewById(R.id.text_sign_up_pass);
        birthDate = (TextView)view.findViewById(R.id.text_sign_up_BirthDate);
        log_in = (TextView)view.findViewById(R.id.already_user);
        sign_up = (Button)view.findViewById(R.id.btn_sign_up);
        log_in.setOnClickListener(this);
        birthDate.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.text_sign_up_BirthDate:

                break;
            case R.id.btn_sign_up:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                        .replace(R.id.background_start_Container,new Log_In(),"Log_In").commit();
                break;
            case R.id.already_user:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                        .replace(R.id.background_start_Container,new Log_In(),"Log_In").commit();
                break;
        }
    }
}
