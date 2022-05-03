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

public class BookingDetailsFragment extends Fragment {
    private TextView email,date,from,to,name,bookdate;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    private static final String TAG = "Fetchr7";
    ProgressDialog progress;
    String finalResult;
    String HttpURL = "http://192.168.64.2/orders/getflightdetails.php";
    private final String MY_QUERY = "select email,date,flightfrom,destination,price,luggage from orders join flight on orders.booked = flight.uuid";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bookingdetails, container, false);

        name= (TextView) v.findViewById(R.id.txtFlightNameCus2);
        email  = (TextView) v.findViewById(R.id.txtFlightEmaiCus2);
        date  = (TextView) v.findViewById(R.id.txtFlightDepDate2);
        from  = (TextView) v.findViewById(R.id.txtFlightDepar2);
        to = (TextView) v.findViewById(R.id.txtFlightDes2);
        bookdate = (TextView) v.findViewById(R.id.txtFlightSaleDate2);

        new FetchItemsTask2().execute(globaluser.a);


        return v;

    }
    class FetchItemsTask2 extends AsyncTask<String,Void, String> {



        @Override
        protected void onPreExecute() {

            //show progress dialog while image is loading
            progress=new ProgressDialog(getActivity());
            progress.setMessage("Loading your favorite places....");
            progress.show();

        }

        @Override
        protected void onPostExecute(String response) {

            Log.i(TAG, "Received JSON: " + response);

            try {
                JSONArray jsonArray = new JSONArray(response);


                    JSONObject obj = jsonArray.getJSONObject(0);

                    //String name = obj.getString("name");
name.setText(obj.getString("last_name")+ " "+  obj.getString("first_name"));
                    email.setText(obj.getString("user_email"));

                    date.setText(obj.getString("date"));
                    from.setText(obj.getString("departure"));
                    to.setText(obj.getString("destination"));
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
    private void parseItems(String json)
            throws IOException, JSONException {


        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String name = obj.getString("name");

            email.setText("cac");
            date.setText(obj.getString("date"));
            from.setText(obj.getString("departure"));
            to.setText(obj.getString("destination"));

        }

    }

    }




