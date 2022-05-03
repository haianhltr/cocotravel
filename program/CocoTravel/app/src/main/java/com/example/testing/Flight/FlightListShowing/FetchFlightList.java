package com.example.testing.Flight.FlightListShowing;


import android.net.Uri;
import android.util.Log;

import com.example.testing.Flight.Flight;

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

public class FetchFlightList {
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

    public List<Flight>  fetchItems() {


        List<Flight> flights= new ArrayList<>();



        try {
            String url = Uri.parse("http://192.168.64.2/LoginRegister/stock_service.php")
                    .buildUpon()

                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            parseItems(flights, jsonString);

        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flights;
    }

    private void parseItems(List<Flight> flights, String json)
            throws IOException, JSONException {


        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String uuidString = obj.getString("flight_id");
            String date1 = obj.getString("date");
            String from = obj.getString("departure");
            String destination = obj.getString("destination");
            String duration1 = obj.getString("duration");
            String transit1 = obj.getString("transit");
            double price1 = obj.getDouble("price");
            int isSolved1 = obj.getInt("luggage");

            Flight flight = new Flight(uuidString);
            flight.setFrom(from);
            flight.setTo(destination);
            flight.setDuration(duration1);
            flight.setTransit(transit1);
            flight.setPrice(price1);
            flight.setSolved(isSolved1 != 0);
            flights.add(flight);

        }



    }

}