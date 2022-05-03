package com.example.testing.others.otherslist;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OtherListFragment extends Fragment {

    private static final String TAG = "Fetchr7";
    private RecyclerView mOtherRecyclerView;
    private OtherAdapter mAdapter;
    private static final String ARG_OTHERS_ID = "others_type";
    String HttpURL = "http://192.168.64.2/others/others.php";
    HttpParse httpParse = new HttpParse();
    Bitmap bitmap = null;
    HashMap<String,String> hashMap = new HashMap<>();
    List<Others> others;
    ProgressDialog progress;
    String finalResult ;

    public static OtherListFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_OTHERS_ID, type);
        OtherListFragment fragment = new OtherListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        //Handler responseHandler = new Handler();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank_list, container, false);
        mOtherRecyclerView = (RecyclerView) view .findViewById(R.id.crime_recycler_view);
        mOtherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String type = (String) getArguments().getSerializable(ARG_OTHERS_ID);
        Log.i(TAG, type);
        new FetchItemsTask2().execute(type);
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


    private class OtherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Others mOther;

        private TextView mName,mType,mAdd;

        private ImageView mImage;

        public OtherHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.list_item_others, parent, false));
            itemView.setOnClickListener(this);


            mImage = (ImageView) itemView.findViewById(R.id.imgOthersImage);
            mName = (TextView) itemView.findViewById(R.id.txtOthersName);
            mType = (TextView) itemView.findViewById(R.id.txtOthersType);
            mAdd = (TextView) itemView.findViewById(R.id.txtOthersAdd);

        }
        @Override
        public void onClick(View view) {
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);

            Toast.makeText(getActivity(), ""+ mOther.getName(), Toast.LENGTH_LONG).show();
            //Intent intent = HotelActivity.newIntent(getActivity(), mHotel.getId());
            // startActivity(intent);
        }
        public void bind(Others other) { mOther = other;

            mName.setText(mOther.getName());
            mType.setText("Type: "+mOther.getType());
            mAdd.setText("Address: "+mOther.getAddress());


            mImage.setImageBitmap(mOther.getImage());


        }

    }

    private class OtherAdapter extends RecyclerView.Adapter<OtherHolder> {
        private List<Others> mOthers;
        public OtherAdapter(List<Others> others) {

            mOthers = others;

        }


        @Override
        public OtherHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new OtherHolder(layoutInflater, parent);

        }
        @Override
        public void onBindViewHolder(OtherHolder holder, int position) {
            Others other = mOthers.get(position);




            holder.bind(other);

        }
        @Override
        public int getItemCount() {
            return mOthers.size(); }

        public void setOthers(List<Others> others) {
            mOthers = others;
        }
    }


    class FetchItemsTask2 extends AsyncTask<String,Void,List<Others>> {


        @Override
        protected void onPreExecute() {

            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your favorite places....");
            progress.show();

        }


        @Override
        protected void onPostExecute(List<Others> others1) {


            others = others1;
            progress.dismiss();

            if (mAdapter == null) {
                mAdapter = new OtherAdapter(others);
                mOtherRecyclerView.setAdapter(mAdapter);}
            else {
                mAdapter.setOthers(others);
                mAdapter.notifyDataSetChanged();
            }



        }
        @Override
        protected List<Others> doInBackground(String... params) {



                hashMap.put("type",params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);




            try {
               others = parseItems(finalResult);



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return others;
        }
    }


    private List<Others>  parseItems(String json)
            throws IOException, JSONException {

        List<Others> res= new ArrayList<>();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String name = obj.getString("name");
            String address = obj.getString("address");
            String type = obj.getString("type");
            String image = obj.getString("url");

            image=image.replace(']','/');
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(image).getContent());

            Others others = new Others();
            others.setType(type);
            others.setName(name);
            others.setAddress(address);
            others.setImage(bitmap);

            res.add(others);




        }



        return res;
    }



}
