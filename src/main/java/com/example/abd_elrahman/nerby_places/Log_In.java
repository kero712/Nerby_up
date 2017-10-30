package com.example.abd_elrahman.nerby_places;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Log_In extends Fragment implements View.OnClickListener {

    EditText email_id,password;
    TextView create_account;
    Button sign_in;

    FragmentManager fragmentManager;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.log__in,container,false);

        UI();



        return view;
    }

    void UI(){
        fragmentManager = getFragmentManager();
        email_id = (EditText)view.findViewById(R.id.text_login_email);
        password = (EditText)view.findViewById(R.id.text_login_pass);
        create_account = (TextView)view.findViewById(R.id.createAccount);
        sign_in = (Button)view.findViewById(R.id.btn_sign_in);
        create_account.setOnClickListener(this);
        sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_sign_in:
                Intent intent = new Intent(getActivity(),Background_Main_Interface.class);
                startActivity(intent);
                break;
            case R.id.createAccount:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                        .replace(R.id.background_start_Container,new Sign_Up(),"Sign_Up").commit();
                break;
        }
    }
}
