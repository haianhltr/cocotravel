package com.example.testing.FamousPlace.PlaceList;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.FamousPlace.Place;
import com.example.testing.R;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceListFragment extends Fragment {
    //private ThumbnailDownloader<PlaceHolder> mThumbnailDownloader;
    private static final String TAG = "Fetchr7";
    private RecyclerView mPlaceRecyclerView;
    private PlaceAdapter mAdapter;
    private static final String ARG_PLACE_ID = "place_type";
    Bitmap bitmap1 = null;
    List<Place> places;
    ProgressDialog progress;


    public static PlaceListFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PLACE_ID, type);
        PlaceListFragment fragment = new PlaceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        String type = (String) getArguments().getSerializable(ARG_PLACE_ID);

        if(type.equals("Top10"))
        {

            new FetchItemsTask2().execute();
        }
      else{
            new FetchItemsTask1().execute();
        }
        //Handler responseHandler = new Handler();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_list, container, false);
        mPlaceRecyclerView = (RecyclerView) view .findViewById(R.id.crime_recycler_view);
        mPlaceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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


    private class PlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private Place mPlace;
        private TextView mName;
        private TextView mRanking;
        private TextView mBus;
        private TextView mType;
        private TextView mDescription;
        private ImageView mImage;

        public PlaceHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_place, parent, false));
            itemView.setOnClickListener(this);

        mDescription = (TextView) itemView.findViewById(R.id.txtShortDescription);
            mImage = (ImageView) itemView.findViewById(R.id.imageView6);
            mName = (TextView) itemView.findViewById(R.id.txtNamePlace);
            mRanking = (TextView) itemView.findViewById(R.id.txtRankingShow);
            mBus = (TextView) itemView.findViewById(R.id.txtBusShow);
            mType = (TextView) itemView.findViewById(R.id.txtTypeShow);
        }
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);

            Toast.makeText(getActivity(), ""+ mPlace.getName(), Toast.LENGTH_LONG).show();
            //Intent intent = HotelActivity.newIntent(getActivity(), mHotel.getId());
           // startActivity(intent);
        }
        public void bind(Place place) { mPlace = place;

        mName.setText(mPlace.getName());
        mRanking.setText(Integer.toString(mPlace.getRanking()));
        //mRanking.setText(mPlace.getRanking());
           // downloadJSON(mPlace.getImage());
            mImage.setImageBitmap(mPlace.getImage());
mDescription.setText(mPlace.getShortdesc());
            Log.i(TAG, "Received JSON: " + mPlace.getImage());
        mBus.setText("Yes");
            Log.i(TAG, "Received JSON: " + mPlace.getType());
        if(mPlace.getType().equals("Saigonese")){

            mType.setText(mPlace.getType()+ " Suggestion");
        }else
            {

              mType.setText(mPlace.getType());
            }


        }

    }

    private class PlaceAdapter extends RecyclerView.Adapter<PlaceHolder> {
        private List<Place> mPlaces;
        public PlaceAdapter(List<Place> places) {

            mPlaces = places;

        }


        @Override
        public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PlaceHolder(layoutInflater, parent);

        }
        @Override
        public void onBindViewHolder(PlaceHolder holder, int position) {
            Place place = mPlaces.get(position);


            //Bitmap bmp = downloadJSON(place.getImage());

            holder.bind(place);
           // mThumbnailDownloader.queueThumbnail(holder, place.getImage());
        }
        @Override
        public int getItemCount() {
            return mPlaces.size(); }

        public void setPlaces(List<Place> places) {
            mPlaces = places;
        }
    }

    private class FetchItemsTask2 extends AsyncTask<Void,Void,List<Place>> {
        @Override
        protected List<Place> doInBackground(Void... params) {
            return new FetchPlace().fetchItems();

        }

        @Override
        protected void onPreExecute() {
            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your favorite places....");
            progress.show();

        }

        @Override
        protected void onPostExecute(List<Place> places1) {

            places = places1;
            progress.dismiss();

            if (mAdapter == null) {
                mAdapter = new PlaceAdapter(places);
                mPlaceRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setPlaces(places);
                mAdapter.notifyDataSetChanged();
            }


        }
    }

    private class FetchItemsTask1 extends AsyncTask<Void,Void,List<Place>> {
        @Override
        protected List<Place> doInBackground(Void... params) {
            return new FetchPlace2().fetchItems();

        }

        @Override
        protected void onPreExecute() {
            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your favorite places....");
            progress.show();

        }

        @Override
        protected void onPostExecute(List<Place> places1) {

            places = places1;
            progress.dismiss();

            if (mAdapter == null) {
                mAdapter = new PlaceAdapter(places);
                mPlaceRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setPlaces(places);
                mAdapter.notifyDataSetChanged();
            }


        }
    }



}
