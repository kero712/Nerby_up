package com.example.abd_elrahman.nerby_places;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abd_elrahman.nerby_places.PlaceModel.PlaceModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class row_details extends AppCompatActivity {


    TextView title,type,hours;
    ImageView poster,love,address;
    RatingBar ratingBar;


    String placeCoverURL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&key=AIzaSyAd7Ga7dxZ2It9hNnFzjxNoa0J8Wnk_Vps&photoreference=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rawitem_details);

        title=(TextView) findViewById(R.id.title_details);
        type=(TextView) findViewById(R.id.category_details);
        hours=(TextView) findViewById(R.id.hours_details);
        poster=(ImageView) findViewById(R.id.img_details);
        love = (ImageView) findViewById(R.id.love_details);

        ratingBar=(RatingBar) findViewById(R.id.rate_details);



        Intent getData = getIntent();
        title.setText(getData.getStringExtra("Title"));
        type.setText(getData.getStringExtra("Category"));
        hours.setText(getData.getStringExtra("Hours"));
        ratingBar.setNumStars(getData.getIntExtra("Rate",1));
        placeCoverURL = placeCoverURL +getData.getStringExtra("Poster") ;
        Picasso.with(this).load(placeCoverURL).into(poster);















        // Intent intent=getIntent();
        PlaceModel placeModel=(PlaceModel) getIntent().getSerializableExtra("placemodels");


        // final byte[]newimg=imageViewtobyte(imageView);




    }

    private byte[] imageViewtobyte(ImageView imageView) {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[]bytearray=stream.toByteArray();
        return bytearray;
    }
}
