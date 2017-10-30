package com.example.abd_elrahman.nerby_places.CustomArrayAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.abd_elrahman.nerby_places.MapsActivity;
import com.example.abd_elrahman.nerby_places.PlaceModel.PlaceModel;
import com.example.abd_elrahman.nerby_places.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.Random;


public class PlaceAdapter extends ArrayAdapter<PlaceModel> {

    View v;
    public PlaceAdapter(@NonNull Context context, @NonNull PlaceModel[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.rawitem_near, parent, false);
        }else
            v = (View)convertView;




        final PlaceModel placeModel=getItem(position);


        ImageView photo = (ImageView) v.findViewById(R.id.img_near);

        assert placeModel != null;
        String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&photoreference="+placeModel.getPhotos().get(0).getPhotoReference()+"&key=AIzaSyAd7Ga7dxZ2It9hNnFzjxNoa0J8Wnk_Vps";
        Picasso.with(getContext()).load(url).into(photo);

        TextView title = (TextView) v.findViewById(R.id.text_title_near);
        title.setText(placeModel.getName());
        TextView hours = (TextView) v.findViewById(R.id.text_hours_near);
        hours.setText(placeModel.getOpeningHours().getOpenNow().toString());
        TextView type = (TextView) v.findViewById(R.id.text_type_near);
        type.setText(placeModel.getTypes().get(0)+"");
        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar_near);
        ratingBar.getRating();

        ImageView nav = (ImageView) v.findViewById(R.id.img_near_nav);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("placeModel",placeModel);
                getContext().startActivity(intent);
            }
        });



        /*
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        v.setBackgroundColor(color);
*/


        return v;

    }


}
