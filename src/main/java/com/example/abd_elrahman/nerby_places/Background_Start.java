package com.example.abd_elrahman.nerby_places;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Background_Start extends AppCompatActivity {

     FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_start);

        fragmentManager = getFragmentManager();

        if(savedInstanceState == null){

            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                    .replace(R.id.background_start_Container,new Log_In(),"Log_In")
                    .commit();
        }



    }
}
