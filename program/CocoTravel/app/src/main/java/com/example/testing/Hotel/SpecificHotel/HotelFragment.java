package com.example.testing.Hotel.SpecificHotel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.Hotel.Hotel;

import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;
import com.example.testing.globaluser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import androidx.fragment.app.Fragment;

public class HotelFragment extends Fragment {
    private static final String ARG_HOTEL_ID = "hotel_id";
    private static final String ARG_HOTEL_ARRIVALDATE = "hotel_arrival";
    private static final String TAG = "Fetchr4";
    String HttpURL = "http://192.168.64.2/hotel/GetSpecificHotel.php";
    String HttpURL2 = "http://192.168.64.2/hotel/BookHotel.php";
    HashMap<String,String> hashMap = new HashMap<>();
    HashMap<String,String> hashMap2 = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String finalResult ;
    private Hotel mHotel;
    private ImageView mImageView;
    private Button mBookButton;
    private TextView mHotelname;
    private TextView mRating;
    private TextView mFromcenter;
    private TextView mAddress;
    private TextView mCheckindate;
    Date date ;


    public static HotelFragment newInstance(String hotelId,String date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOTEL_ID, hotelId);
        args.putSerializable(ARG_HOTEL_ARRIVALDATE,date);
        HotelFragment fragment = new HotelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mHotel = HotelLab.get(getActivity()).getHotel(hotelId);

    }

    /*@Override
    public void onPause() {
        super.onPause();
        HotelLab.get(getActivity())
                .updateHotel(mHotel);
    }
*/



    public void GetHotelFunction(final String id){

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


                hashMap.put("hotel_id",params[0]);


                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();


        userLoginClass.execute(id);
    }


    public void BookHotel(final String customerID, final String hotelID,final String date){



        class BookHotelClass extends AsyncTask<String,Void,String> {



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
                hashMap2.put("hotel_id",params[1]);
                hashMap2.put("checkin_date",params[2]);


                finalResult = httpParse.postRequest(hashMap2, HttpURL2);

                return finalResult;
            }
        }

        BookHotelClass bookhotelclass = new BookHotelClass();


        bookhotelclass.execute(customerID,hotelID,date);
    }










    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hotel, container, false);
        String hotelId = (String) getArguments().getSerializable(ARG_HOTEL_ID);
        String checkindate = (String) getArguments().getSerializable(ARG_HOTEL_ARRIVALDATE);
        Log.i(TAG, "Received JSON43: " + checkindate);
        GetHotelFunction(hotelId);

        mHotelname = (TextView) v.findViewById(R.id.idHotelName1);



        mRating = (TextView ) v.findViewById(R.id.idRating1);

        mFromcenter = (TextView ) v.findViewById(R.id.idFromCenter1);



        mAddress = (TextView ) v.findViewById(R.id.idAddress1);


        mImageView = (ImageView) v.findViewById(R.id.imageView4);
        mCheckindate= (TextView) v.findViewById(R.id.txtCheckinDate);
        mCheckindate.setText("     Your checkin date: "+ checkindate);


       // Bitmap b1 = BitmapFactory.decodeByteArray(mHotel.getImage(), 0, mHotel.getImage().length);
        //mImageView.setImageBitmap(b1);



        mBookButton = (Button) v.findViewById(R.id.btnBookHotel);
        mBookButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                BookHotel(globaluser.a,hotelId,checkindate);

                Toast.makeText(getActivity(), "Booked, you can check your hotel details",
                        Toast.LENGTH_LONG).show();



            }
        });





        return v;
    }

    private void parseItems(String json)
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

        mHotel = hotel;
            Log.i(TAG, "Received JSON: " + mHotel.getId() + mHotel.getName() + mHotel.getRate());
        }



       mHotelname.setText(mHotel.getName());
      mRating.setText(mHotel.getRate().toString());
        mFromcenter.setText(mHotel.getFromcenter() + "km from center");
        mAddress.setText(mHotel.getAddress()+ ", District " + mHotel.getDistrict()+ ", " + mHotel.getCity());
    }


}