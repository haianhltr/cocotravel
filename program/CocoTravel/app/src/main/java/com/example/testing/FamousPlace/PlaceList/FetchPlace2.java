package com.example.testing.FamousPlace.PlaceList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.testing.FamousPlace.Place;

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

public class FetchPlace2 {
    private static final String TAG = "Fetchr2";
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

    public List<Place> fetchItems() {



        List<Place> places= new ArrayList<>();


        try {
            String url = Uri.parse("http://192.168.64.2/place/placebySGS.php")
                    .buildUpon()

                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            parseItems(places, jsonString);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return places;
    }

    private void parseItems(List<Place> places, String json)
            throws IOException, JSONException {


        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);



            String id = obj.getString("place_id");
            String name = obj.getString("name");
            int ranking = obj.getInt("ranking");
            String category = obj.getString("category");

            String address = obj.getString("address");
            String district = obj.getString("district");
            String image = obj.getString("image");
            String shortdesc = obj.getString("shortdescription");
            image=image.replace(']','/');
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image).getContent());

            Log.i(TAG, "Received JSON: " + image);
            int bus = obj.getInt("bus_support");

            Place place = new Place();
            place.setID(id);
            place.setName(name);
            place.setRanking(ranking);
            place.setType(category);
            place.setAddress(address);
            place.setDistrict(district);
            place.setImage(bitmap);
            place.setShortdesc(shortdesc);
            place.setBus(bus != 0);

            places.add(place);




        }



    }}
