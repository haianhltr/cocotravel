package com.example.testing.Hotel.HotelListShowing;

import android.net.Uri;
import android.util.Log;

import com.example.testing.Hotel.Hotel;

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

public class FetchHotelList {
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

    public List<Hotel> fetchItems() {


        List<Hotel> hotels= new ArrayList<>();



        try {
            String url = Uri.parse("http://192.168.64.2/hotel/hotel.php")
                    .buildUpon()

                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            parseItems(hotels, jsonString);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    private void parseItems(List<Hotel> hotels, String json)
            throws IOException, JSONException {


        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String uuidString = obj.getString("hotel_id");
            String city = obj.getString("city");
            String district = obj.getString("district");
            String name = obj.getString("name");
            double rating = obj.getDouble("rating");
            String address = obj.getString("address");
            double fromcenter = obj.getDouble("fromcenter");
            int breakfast = obj.getInt("breakfast");
            double price = obj.getDouble("price");
            int airportpickup = obj.getInt("airportpickup");



            Hotel hotel = new Hotel(uuidString);
            hotel.setCity(city);
            hotel.setDistrict(district);
            hotel.setName(name);
            hotel.setRate(rating);
            hotel.setAddress(address);
            hotel.setFromcenter(fromcenter);
            hotel.setBreakfast(breakfast != 0);
            hotel.setPrice(price);
            hotel.setTransportation(airportpickup != 0);

            hotels.add(hotel);

        }



    }

}