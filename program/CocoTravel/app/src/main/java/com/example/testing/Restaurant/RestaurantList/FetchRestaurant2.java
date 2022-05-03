package com.example.testing.Restaurant.RestaurantList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchRestaurant2 {


    public List<Restaurant> fetchItems(String jsonString) throws IOException, JSONException {



        List<Restaurant> restaurants= new ArrayList<>();
        parseItems(restaurants, jsonString);


        return restaurants;
    }

    private void parseItems(List<Restaurant> restaurants, String json)
            throws IOException, JSONException {


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

            restaurants.add(restaurant);






        }



    }}
