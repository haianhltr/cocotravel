package com.example.testing.Flight.SpecificFlight;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Flight.Flight;
import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;
import com.example.testing.globaluser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import androidx.fragment.app.Fragment;

public class FlightFragment extends Fragment {
Date dateconvert = new Date();
    private static final String TAG = "Fetchr3";
    String HttpURL = "http://192.168.64.2/flight/getspecificflight.php";
    String HttpURL2 = "http://192.168.64.2/flight/bookflight.php";
    HashMap<String,String> hashMap = new HashMap<>();
    HashMap<String,String> hashMap2 = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    private static final String ARG_CRIME_ID = "flight_id";
    String finalResult ;
    private Flight mFlight;
    private Button mDateButton;
    private Button mBookButton;
    private TextView mTitle;
    private TextView mTitle1;
    private TextView mPrice;
    private CheckBox mSolvedCheckBox;



    public static FlightFragment newInstance(String crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        FlightFragment fragment = new FlightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void GetFlightFunction(final String id){

        class UserLoginClass extends AsyncTask<String,Void,String> {



            @Override
            protected void onPostExecute(String httpResponseMsg)  {

                super.onPostExecute(httpResponseMsg);
                Log.i(TAG, "Received JSON: " + httpResponseMsg);
                try {
                    parseItems(httpResponseMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {


                hashMap.put("id",params[0]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();


        userLoginClass.execute(id);
    }



    public void BookFlight(final String customerID, final String flightID){



        class BookFlightClass extends AsyncTask<String,Void,String> {



            @Override
            protected void onPostExecute(String httpResponseMsg)  {

                super.onPostExecute(httpResponseMsg);
                Log.i(TAG, "Received JSON: " + httpResponseMsg);
                try {
                    parseItems(httpResponseMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(String... params) {


                hashMap2.put("customer_id",params[0]);
                hashMap2.put("flight_id",params[1]);



                finalResult = httpParse.postRequest(hashMap2, HttpURL2);

                return finalResult;
            }
        }

        BookFlightClass bookflightclass = new BookFlightClass();


        bookflightclass.execute(customerID,flightID);
    }











    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_flight, container, false);
        mTitle1 = (TextView) v.findViewById(R.id.flight_starting);
        mTitle = (TextView) v.findViewById(R.id.flight_title);
        mPrice = (TextView) v.findViewById(R.id.flight_price);

        mDateButton = (Button) v.findViewById(R.id.datebutton);
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.carryon);
        String flightId = (String) getArguments().getSerializable(ARG_CRIME_ID);
        GetFlightFunction(flightId);





        mBookButton = (Button) v.findViewById(R.id.btnBookFlight);
        mBookButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                BookFlight(globaluser.a,flightId);

                Toast.makeText(getActivity(), "Booked, you can check your flight details",
                        Toast.LENGTH_LONG).show();


            }
        });




        return v;
    }
    private void parseItems(String json)
            throws IOException, JSONException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


        try {
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



           Date date3 =  sdf.parse(date1);


            Flight flight = new Flight(uuidString);
            flight.setDate(date3);
            flight.setFrom(from);
            flight.setTo(destination);
            flight.setDuration(duration1);
            flight.setTransit(transit1);
            flight.setPrice(price1);
            flight.setSolved(isSolved1 != 0);
            mFlight = flight;
            Log.i(TAG, "Received JSON: " + mFlight.getId() + mFlight.getTo() + mFlight.getDuration());

        }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        mTitle1.setText(mFlight.getFrom());

        mTitle.setText(mFlight.getFrom()+" ->" + mFlight.getTo());


        mPrice.setText(mFlight.getPrice().toString());



       mDateButton.setText(mFlight.getDate().toString());
        //mDateButton.setEnabled(false);




        mSolvedCheckBox.setChecked(mFlight.isSolved());
        mSolvedCheckBox.setEnabled(false);


    }

}