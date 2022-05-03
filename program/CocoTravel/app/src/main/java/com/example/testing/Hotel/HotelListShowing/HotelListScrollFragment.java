package com.example.testing.Hotel.HotelListShowing;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testing.Hotel.Hotel;
import com.example.testing.Hotel.SpecificHotel.HotelActivity;
import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HotelListScrollFragment extends Fragment {
    String HttpURL = "http://192.168.64.2/hotel/hotelspecific.php";
    HttpParse httpParse = new HttpParse();
    private static final String ARG_HOTEL_DISTRICT = "hotel_district";
    private static final String ARG_HOTEL_ARRIVALDATE = "hotel_arrival";
    private static final String ARG_ALL_SPECIFIC = "flight_check";
    private RecyclerView mHotelRecyclerView;
    private HotelListScrollFragment.HotelAdapter mAdapter;
    HashMap<String,String> hashMap = new HashMap<>();
    String finalResult ;

    List<Hotel> hotels;
    public static HotelListScrollFragment newInstance(String district, String date,String check) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_HOTEL_DISTRICT, district);
        args.putSerializable(ARG_ALL_SPECIFIC,check);
        args.putSerializable(ARG_HOTEL_ARRIVALDATE, date);
        HotelListScrollFragment fragment = new HotelListScrollFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mHotelRecyclerView = (RecyclerView) view .findViewById(R.id.crime_recycler_view);
        mHotelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //updateUI();
        String district = (String) getArguments().getSerializable(ARG_HOTEL_DISTRICT);
        String check = (String) getArguments().getSerializable(ARG_ALL_SPECIFIC);
        if(check.equals("Specific"))
        {
            new FetchItemsTask().execute(district);
        }
        else{
            new FetchItemsTask2().execute();
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //updateUI();
    }


    private class HotelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private Hotel mHotel;
        private TextView mDistrict;
        private TextView mName;
        private TextView mRate;
        private TextView mPrice;


        public HotelHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_hotel, parent, false));
            itemView.setOnClickListener(this);


          mDistrict = (TextView) itemView.findViewById(R.id.idDistrict);
          mName = (TextView) itemView.findViewById(R.id.idHotelName);
          mRate = (TextView) itemView.findViewById(R.id.idRatingHotel);
          mPrice = (TextView) itemView.findViewById(R.id.idHotelPrice);
        }
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);
            String date = (String) getArguments().getSerializable(ARG_HOTEL_ARRIVALDATE);

          Intent intent = HotelActivity.newIntent(getActivity(), mHotel.getId(),date);
            startActivity(intent);
        }
        public void bind(Hotel hotel) { mHotel = hotel;
            mDistrict.setText("District "+mHotel.getDistrict());
            mName.setText(mHotel.getName());
            mRate.setText("Rating "+mHotel.getRate().toString());
            mPrice.setText("$"+mHotel.getPrice().toString());

        }

    }

    private class HotelAdapter extends RecyclerView.Adapter<HotelHolder> {
        private List<Hotel> mHotels;
        public HotelAdapter(List<Hotel> hotels) {
            mHotels = hotels;

        }


        @Override
        public HotelHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HotelHolder(layoutInflater, parent); }
        @Override
        public void onBindViewHolder(HotelHolder holder, int position) {
            Hotel hotel = mHotels.get(position);
            holder.bind(hotel);
        }
        @Override
        public int getItemCount() {
            return mHotels.size(); }

        public void setHotels(List<Hotel> hotels) {
           mHotels = hotels;
        }
    }

    class FetchItemsTask extends AsyncTask<String,Void,List<Hotel>> {
        @Override
        protected List<Hotel> doInBackground(String... params) {



                hashMap.put("district",params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);







            //finalResult = httpParse.postRequest(hashMap, HttpURL);
            try {
                hotels = parseItems(finalResult);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //return new FetchFlightList().fetchItems();
            return hotels;
        }
        @Override
        protected void onPostExecute(List<Hotel> hotels1) {
            hotels= hotels1;

            if (mAdapter == null) {
                mAdapter = new HotelAdapter(hotels);
                mHotelRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setHotels(hotels);
                mAdapter.notifyDataSetChanged();
            }


        }
    }
    private List<Hotel> parseItems(String json)
            throws IOException, JSONException {
        List<Hotel> res= new ArrayList<>();

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

            res.add(hotel);

        }

        return res;

    }



    private class FetchItemsTask2 extends AsyncTask<Void,Void,List<Hotel>> {
        @Override
        protected List<Hotel> doInBackground(Void... params) {
            return new FetchHotelList().fetchItems();

        }
        @Override
        protected void onPostExecute(List<Hotel> hotels1) {
            hotels = hotels1;


            if (mAdapter == null) {
                mAdapter = new HotelAdapter(hotels);
                mHotelRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setHotels(hotels);
                mAdapter.notifyDataSetChanged();
            }


        }
    }


}
