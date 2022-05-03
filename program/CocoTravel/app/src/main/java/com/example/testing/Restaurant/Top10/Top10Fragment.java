package com.example.testing.Restaurant.Top10;

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

import com.example.testing.R;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Top10Fragment extends Fragment {

    private static final String TAG = "Fetchr7";
    private RecyclerView mFoodRecyclerView;
    private FoodAdapter mAdapter;
    Bitmap bitmap1 = null;
    List<Food> foods;
    ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        new FetchItemsTask2().execute();
        //Handler responseHandler = new Handler();

        Log.i(TAG, "Background thread started");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_list, container, false);
     mFoodRecyclerView = (RecyclerView) view .findViewById(R.id.crime_recycler_view);
       mFoodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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


    private class FoodHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Food mFood;
        private TextView mName;
        private TextView mName2;
        private ImageView mImage;
        private  TextView mDescription;



        public FoodHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_food, parent, false));
            itemView.setOnClickListener(this);


            mImage = (ImageView) itemView.findViewById(R.id.imgFood);
            mName = (TextView) itemView.findViewById(R.id.txtFoodname);
            mDescription = (TextView) itemView.findViewById(R.id.txtShortDescFood);
            mName2 = (TextView) itemView.findViewById(R.id.txtAltName2);



        }
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);

            Toast.makeText(getActivity(), ""+ mFood.getName(), Toast.LENGTH_LONG).show();
            //Intent intent = HotelActivity.newIntent(getActivity(), mHotel.getId());
            // startActivity(intent);
        }
        public void bind(Food food) { mFood = food;

            mName.setText(mFood.getName());
            mName2.setText(mFood.getAltname());
            mDescription.setText(mFood.getDescription());
            mImage.setImageBitmap(mFood.getImage());
        }

    }

    private class FoodAdapter extends RecyclerView.Adapter<FoodHolder> {
        private List<Food> mFoods;
        public FoodAdapter(List<Food> foods) {

            mFoods = foods;

        }


        @Override
        public FoodHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new FoodHolder(layoutInflater, parent);

        }
        @Override
        public void onBindViewHolder(FoodHolder holder, int position) {

           Food food = mFoods.get(position);

            //Bitmap bmp = downloadJSON(place.getImage());

            holder.bind(food);
            // mThumbnailDownloader.queueThumbnail(holder, place.getImage());
        }
        @Override
        public int getItemCount() {
            return mFoods.size(); }

        public void setFoods(List<Food> foodss) {
            mFoods = foods;
        }
    }

    private class FetchItemsTask2 extends AsyncTask<Void,Void,List<Food>> {
        @Override
        protected List<Food> doInBackground(Void... params) {
            return new FetchFood().fetchItems();

        }

        @Override
        protected void onPreExecute() {
            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your favorite food....");
            progress.show();

        }

        @Override
        protected void onPostExecute(List<Food> foods1) {

            foods =foods1;
            progress.dismiss();

            if (mAdapter == null) {
                mAdapter = new FoodAdapter(foods);
               mFoodRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setFoods(foods);
                mAdapter.notifyDataSetChanged();
            }


        }
    }



}
