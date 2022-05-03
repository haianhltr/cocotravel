package com.example.testing.BookingDetails.BookingDetailsFragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testing.LoginAndRegister.HttpParse;
import com.example.testing.R;
import com.example.testing.globaluser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import androidx.fragment.app.Fragment;

public class BookingHotelFragment extends Fragment {
    private TextView email,date,hotelname,address,name,bookdate;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    private static final String TAG = "Fetchr7";
    ProgressDialog progress;
    String finalResult;
    String HttpURL = "http://192.168.64.2/orders/gethoteldetails.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bookingdetailshotel, container, false);

        name= (TextView) v.findViewById(R.id.idNameOfCus);
        email  = (TextView) v.findViewById(R.id.idEmailCus);
        date  = (TextView) v.findViewById(R.id.idBookHotelCheckInDate);
        hotelname = (TextView) v.findViewById(R.id.idBookHotelName);
        address = (TextView) v.findViewById(R.id.idBookHotelAddress);
        bookdate = (TextView) v.findViewById(R.id.idBookHotelBookON);

        new FetchItemsTask2().execute(globaluser.a);


        return v;

    }
    class FetchItemsTask2 extends AsyncTask<String,Void, String> {



        @Override
        protected void onPreExecute() {

            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your booking information....");
            progress.show();

        }

        @Override
        protected void onPostExecute(String response) {

            Log.i(TAG, "Received JSON: " + response);

            try {
                JSONArray jsonArray = new JSONArray(response);


                JSONObject obj = jsonArray.getJSONObject(0);

                //String name = obj.getString("name");
                name.setText(obj.getString("last_name")+" "+  obj.getString("first_name"));
                email.setText(obj.getString("user_email"));
                date.setText(obj.getString("checkinDate"));


                hotelname.setText(obj.getString("name"));
                address.setText(obj.getString("address"));
                bookdate.setText(obj.getString("SaleDate"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progress.dismiss();



        }
        @Override
        protected String doInBackground(String... params) {
            // return new FetchRestaurant().fetchItems();




            hashMap.put("customer_id",params[0]);

            finalResult = httpParse.postRequest(hashMap, HttpURL);



            return finalResult;
        }
    }


}




