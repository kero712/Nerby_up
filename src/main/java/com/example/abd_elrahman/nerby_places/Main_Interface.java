package com.example.abd_elrahman.nerby_places;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.abd_elrahman.nerby_places.CustomArrayAdapter.PlaceAdapter;
import com.example.abd_elrahman.nerby_places.PlaceModel.PlaceModel;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;






public class Main_Interface extends Fragment {

    FragmentManager fragmentManager;
    View view;

    Button Search;
    EditText text;
    ListView lstplaces,lst_fav_places;
    Gson gson =new Gson();
    ProgressDialog progressDialog ;
    PlaceModel[] placemodels;
    PlaceAdapter placesadapter;
   // DatabaseHandler db=new DatabaseHandler(getActivity());


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_interface, container, false);

        //-------------------------------------------
        fragmentManager = getFragmentManager();
        lstplaces = (ListView)view.findViewById(R.id.list_places);




        lst_fav_places=(ListView)view.findViewById(R.id.lst_fav_places);




        text = (EditText)view.findViewById(R.id.SearchView);
        Search =(Button) view.findViewById(R.id.btn_SearchView);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = text.getText().toString();

                // AIzaSyDZJyMt5I-mNd9N3AHQha5IKw3eO5Z35XM


                String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type="+query+"&keyword=cruise&key=AIzaSyAd7Ga7dxZ2It9hNnFzjxNoa0J8Wnk_Vps";
                Log.d("zamel", "onClick: "+url);
                GetPlaces getPlaces =new GetPlaces();
                getPlaces.execute(url);
            }
        });

        return view;
    }


   private class GetPlaces extends AsyncTask<String , Void ,PlaceModel[] > {

        protected void onPreExecute() {
            progressDialog =new ProgressDialog(getActivity());
            progressDialog.setMessage("loading.....");
            progressDialog.show();
        }
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

        protected PlaceModel[] doInBackground(String... url) {
            try {
                String s = run(url[0]);
                Log.d("hesham", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                placemodels = gson.fromJson(jsonArray.toString(), PlaceModel[].class);
                Log.d("zamel", "doInBackground: "+placemodels.length);
                return placemodels;
            }
            catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(final PlaceModel[]placesModels) {
            progressDialog.dismiss();
            if(placesModels!=null) {
                placesadapter = new PlaceAdapter(getActivity(), placemodels);
                lstplaces.setAdapter(placesadapter);
                lstplaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
                        /*
                        Intent intent = new Intent(getActivity(),row_details.class);
                        intent.putExtra("Poster", (Parcelable) placesModels[i].getPhotos());
                        intent.putExtra("Title",placemodels[i].getName());
                        intent.putExtra("Category",placemodels[i].getTypes().toString());
                        intent.putExtra("Hours",placemodels[i].getOpeningHours());
                        intent.putExtra("Rate",placemodels[i].getRating());
                        startActivity(intent);
*/
                    }
                });

            }
        }



    }


}


