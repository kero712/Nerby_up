package com.example.abd_elrahman.nerby_places;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Background_Main_Interface extends AppCompatActivity {

    FragmentManager fragmentManager;
    ImageView img_logout, img_like;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_main_interface);
        fragmentManager = getFragmentManager();
        img_logout = (ImageView) findViewById(R.id.logout);
        img_like = (ImageView) findViewById(R.id.like);


        if(savedInstanceState == null){
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                    .replace(R.id.background_main_Container,new Main_Interface(),"Main_Interface").commit();
        }

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Background_Main_Interface.this)
                        .setIcon(R.drawable.ic_warning)
                        .setTitle("Logging Out")
                        .setMessage("Are you Sure You Want To Sign Out ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        // AIzaSyAc0OpBbT-OfK8QgoZTpo2-c5T4IOaLApU

        // https://maps.googleapis.com/maps/api/place/textsearch/json?
        // query=123+main+street
        // &location=27.3390256,34.2727533
        // &radius=10000
        // &key=AIzaSyAc0OpBbT-OfK8QgoZTpo2-c5T4IOaLApU





        // -----------------------


        // https://maps.googleapis.com/maps/api/place/nearbysearch/json?
        // location=27.3390256,34.2727533
        // &radius=500
        // &type= what i type !!!
        // &keyword=cruise
        // &key=AIzaSyAc0OpBbT-OfK8QgoZTpo2-c5T4IOaLApU
        img_like.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final boolean[] showingFirst = {true};

                img_like.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(showingFirst[0]){
                            img_like.setImageResource(R.drawable.ic_favorite_red);
                            fragmentManager
                                    .beginTransaction()
                                    .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                                    .replace(R.id.background_main_Container,new Favourite(),"Favourite").commit();
                            showingFirst[0] = false;
                        }else{
                            img_like.setImageResource(R.drawable.ic_favorite_white);
                            fragmentManager
                                    .beginTransaction()
                                    .setCustomAnimations(R.animator.right_enter,R.animator.left_exit)
                                    .replace(R.id.background_main_Container,new Main_Interface(),"Main_Interface").commit();
                            img_like.setTag(70);
                            showingFirst[0] = true;
                        }
                    }
                });
            }
        });





    }



}


