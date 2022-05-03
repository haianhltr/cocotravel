package com.example.testing.Restaurant.RestaurantList;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantListFragment extends Fragment {
    //private ThumbnailDownloader<PlaceHolder> mThumbnailDownloader;
    private static final String TAG = "Fetchr7";
    private RecyclerView mRestaurantRecyclerView;
    private RestaurantAdapter mAdapter;
    Bitmap bitmap1 = null;
    String HttpURL = "http://192.168.64.2/restaurant/restaurant.php";
    String HttpURL2 = "http://192.168.64.2/restaurant/getcountryfood.php";
    private static final String ARG_RESTAU_ID = "restau_type";
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    List<Restaurant> restaurants;
    ProgressDialog progress;
    String finalResult ;
    FetchRestaurant2 fetch = new FetchRestaurant2();

    public static RestaurantListFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RESTAU_ID, type);
        RestaurantListFragment fragment = new RestaurantListFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        //Handler responseHandler = new Handler();

        Log.i(TAG, "Background thread started");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_list, container, false);
        mRestaurantRecyclerView = (RecyclerView) view .findViewById(R.id.crime_recycler_view);
        mRestaurantRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String type = (String) getArguments().getSerializable(ARG_RESTAU_ID);
         new FetchItemsTask2().execute(type);
        //updateUI();

        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // mThumbnailDownloader.quit();
        Log.i(TAG, "Background thread destroyed");
    }

    @Override
    public void onResume() {
        super.onResume();
        //updateUI();
    }


    private class RestaurantHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Restaurant mRestaurant;
        private TextView mName;
        private ImageView mImage;
        private TextView mAddress;
        private TextView mType;
        private TextView mCountry;

        public RestaurantHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_dining, parent, false));
            itemView.setOnClickListener(this);


            mImage = (ImageView) itemView.findViewById(R.id.imgDining);
            mName = (TextView) itemView.findViewById(R.id.txtDiningName);
            mAddress = (TextView) itemView.findViewById(R.id.txtDiningAdd);
            mType = (TextView) itemView.findViewById(R.id.txtDiningType);
            mCountry = (TextView) itemView.findViewById(R.id.txtDiningCountry);
        }
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);

            Toast.makeText(getActivity(), ""+ mRestaurant.getName(), Toast.LENGTH_LONG).show();
            //Intent intent = HotelActivity.newIntent(getActivity(), mHotel.getId());
            // startActivity(intent);
        }
        public void bind(Restaurant restaurant) { mRestaurant = restaurant;

        mName.setText(mRestaurant.getName());
            mAddress.setText("Address: "+mRestaurant.getAddress());
            mType.setText("Type Of Dining: "+ mRestaurant.getType2());
            mCountry.setText("Country: "+mRestaurant.getCountry());
           mImage.setImageBitmap(mRestaurant.getImage());
        }

    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder> {
        private List<Restaurant> mRestaurants;
        public RestaurantAdapter(List<Restaurant> restaurants) {

            mRestaurants = restaurants;

        }


        @Override
        public RestaurantHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new RestaurantHolder(layoutInflater, parent);

        }
        @Override
        public void onBindViewHolder(RestaurantHolder holder, int position) {

Restaurant restaurant = mRestaurants.get(position);

            //Bitmap bmp = downloadJSON(place.getImage());

            holder.bind(restaurant);
            // mThumbnailDownloader.queueThumbnail(holder, place.getImage());
        }
        @Override
        public int getItemCount() {
            return mRestaurants.size(); }

        public void setRestaurants(List<Restaurant> restaurants) {
            mRestaurants = restaurants;
        }
    }









         class FetchItemsTask2 extends AsyncTask<String,Void,List<Restaurant>> {


        @Override
        protected void onPreExecute() {

            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your favorite places....");
            progress.show();

        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurants1) {

            //Log.i(TAG, "Received JSON: " + httpresponse);

            restaurants = restaurants1;
            progress.dismiss();

            if (mAdapter == null) {
                mAdapter = new RestaurantAdapter(restaurants);
                mRestaurantRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setRestaurants(restaurants);
                mAdapter.notifyDataSetChanged();
            }



        }
             @Override
             protected List<Restaurant> doInBackground(String... params) {
                 // return new FetchRestaurant().fetchItems();


               if(params[0].equals("Dining") || params[0].equals("Drinks")){

                   hashMap.put("type",params[0]);

                   finalResult = httpParse.postRequest(hashMap, HttpURL);
               }
               else{

                   hashMap.put("country",params[0]);

                   finalResult = httpParse.postRequest(hashMap, HttpURL2);
               }



                 try {
                     restaurants = parseItems(finalResult);



                 } catch (IOException e) {
                     e.printStackTrace();
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

                 return restaurants;
             }
    }


    private List<Restaurant>  parseItems(String json)
            throws IOException, JSONException {

        List<Restaurant> res= new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String name = obj.getString("name");
            String address = obj.getString("address");
            String type = obj.getString("type");
            String country = obj.getString("country");
            String type2 = obj.getString("typeofdining");
            String image = obj.getString("url");

            image=image.replace(']','/');
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image).getContent());
            //image=image.replace(']','/');
            //Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image).getContent());

            //Log.i(TAG, "Received JSON: " + image);

            Restaurant restaurant = new Restaurant();
            restaurant.setType(type);
            restaurant.setName(name);
            restaurant.setAddress(address);
            restaurant.setCountry(country);
            restaurant.setType2(type2);
            restaurant.setImage(bitmap);

        res.add(restaurant);




        }



     return res;
    }




}
