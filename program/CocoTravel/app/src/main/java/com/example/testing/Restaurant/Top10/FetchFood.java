package com.example.testing.Restaurant.Top10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchFood {
    private static final String TAG = "Cac";
    private static final String API_KEY = "yourApiKeyHere";


    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +

                        ": with " +
                        urlSpec);}
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<Food> fetchItems() {



        List<Food> foods= new ArrayList<>();


        try {
            String url = Uri.parse("http://192.168.64.2/restaurant/topfood.php")
                    .buildUpon()

                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            parseItems(foods, jsonString);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foods;
    }

    private void parseItems(List<Food> foods, String json)
            throws IOException, JSONException {


        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);




            String name = obj.getString("name");
            String vietnamese_name = obj.getString("vietnamese_name");
            String description = obj.getString("description");
            String image = obj.getString("url");

            image=image.replace(']','/');
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image).getContent());


           Food food = new Food();
            food.setName(name);
            food.setAltname(vietnamese_name);
            food.setDescription(description);
            food.setImage(bitmap);



          foods.add(food);






        }



    }}
