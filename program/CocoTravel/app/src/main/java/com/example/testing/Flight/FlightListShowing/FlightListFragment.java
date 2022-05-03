package com.example.testing.Flight.FlightListShowing;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.testing.Flight.Flight;
import com.example.testing.Flight.SpecificFlight.FlightActivity;

import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightListFragment extends Fragment {
    Button button4;
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.64.2/flight/getflight.php";
    String HttpURL2 = "http://192.168.64.2/flight/getflightall.php";
    private static final String TAG = "PhotoGalleryFragment2";
    private static final String ARG_FLIGHT_FROM = "flight_from";
    private static final String ARG_FLIGHT_TO = "flight_to";
    private static final String ARG_FLIGHT_DATE = "flight_date";
    private static final String ARG_ALL_SPECIFIC = "flight_check";
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Flight mFlight;
    HashMap<String,String> hashMap = new HashMap<>();
    String finalResult ;


    List<Flight> flights;
    public static FlightListFragment newInstance(String from, String to, String date, String check) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FLIGHT_FROM, from);
        args.putSerializable(ARG_FLIGHT_TO, to);
        args.putSerializable(ARG_FLIGHT_DATE, date);
        args.putSerializable(ARG_ALL_SPECIFIC,check);
        FlightListFragment fragment = new FlightListFragment();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String date = (String) getArguments().getSerializable(ARG_FLIGHT_DATE);
        String flightfrom = (String) getArguments().getSerializable(ARG_FLIGHT_FROM);
        String check = (String) getArguments().getSerializable(ARG_ALL_SPECIFIC);
        Log.i(TAG, "Received date: " + date);
        Log.i(TAG, "Received check: " + check);
        //updateUI();



        new FetchItemsTask().execute(check, date,flightfrom);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //updateUI();
    }


    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Flight mFlight;
        private TextView mFlightTitle;
        private TextView mDuration;
        private TextView mTransit;
        private TextView mPrice;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_flight, parent, false));
            itemView.setOnClickListener(this);


            mFlightTitle = (TextView) itemView.findViewById(R.id.flight_title);
            mDuration = (TextView) itemView.findViewById(R.id.flight_duration);
            mTransit = (TextView) itemView.findViewById(R.id.flight_transit);
            mPrice = (TextView) itemView.findViewById(R.id.flight_price);
        }
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);


            Intent intent = FlightActivity.newIntent(getActivity(), mFlight.getId());
            startActivity(intent);
        }
        public void bind(Flight flight) { mFlight = flight;
            mFlightTitle.setText(mFlight.getFrom()+" -> " + mFlight.getTo());
            mDuration.setText(mFlight.getDuration());
            mTransit.setText(mFlight.getTransit());
            mPrice.setText(mFlight.getPrice().toString());
        }

    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Flight> mFlights;
        public CrimeAdapter(List<Flight> flights) {
            mFlights = flights;

        }


        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent); }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Flight flight = mFlights.get(position);
            holder.bind(flight);
        }
        @Override
        public int getItemCount() {
            return mFlights.size(); }

        public void setFlights(List<Flight> flights) {
            mFlights = flights;
        }
    }


    private class FetchItemsAll extends AsyncTask<Void,Void,List<Flight>> {
        @Override
        protected List<Flight> doInBackground(Void... params) {
            return new FetchFlightList().fetchItems();

        }
        @Override
        protected void onPostExecute(List<Flight> flights1) {
            flights = flights1;

            if (mAdapter == null) {
                mAdapter = new CrimeAdapter(flights);
                mCrimeRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setFlights(flights);
                mAdapter.notifyDataSetChanged();
            }

        }
    }




    class FetchItemsTask extends AsyncTask<String,Void,List<Flight>> {
        @Override
        protected List<Flight> doInBackground(String... params) {
            Log.i(TAG, "Received JSOdfasfN: " + params[0]);
            Log.i(TAG, "Received JSOdfasfN: " + params[1]);
            Log.i(TAG, "Received JSOdfasfN: " + params[2]);
            if(params[0].equals("All"))
            {

                hashMap.put("date",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL2);
                Log.i(TAG, "Received JSON: " + finalResult);
            }
            else{

                hashMap.put("date",params[1]);
                hashMap.put("from",params[2]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                Log.i(TAG, "Received JSON: " + finalResult);
            }


            //finalResult = httpParse.postRequest(hashMap, HttpURL);
            try {
               flights = parseItems(finalResult);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //return new FetchFlightList().fetchItems();
            return flights;
        }
        @Override
        protected void onPostExecute(List<Flight> flights1) {
            flights = flights1;

            if (mAdapter == null) {
                mAdapter = new CrimeAdapter(flights);
                mCrimeRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setFlights(flights);
                mAdapter.notifyDataSetChanged();
            }

        }
    }
    private List<Flight> parseItems(String json)
            throws IOException, JSONException {
        List<Flight> res= new ArrayList<>();

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
            res.add(flight);

        }

return res;

    }
}



