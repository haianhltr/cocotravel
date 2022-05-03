package com.example.testing.Restaurant.Menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.R;
import com.example.testing.Restaurant.RestaurantList.RestaurantList;
import com.example.testing.Restaurant.Top10.Top10Food;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DiningMenuFragment extends Fragment
{
    String check = "Or, any country food";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_COUNTRY = 0;
    private static final String TAG = "state";
    Button topVNfood, getRestau,getDrinks,getCountryFood;
    TextView showcountry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pickdining, container, false);




        topVNfood = v.findViewById(R.id.btnTopVnFood);
       topVNfood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {




                Intent intent = new Intent(getActivity(), Top10Food.class);
                startActivity(intent);



            }
        });

        getRestau = v.findViewById(R.id.btnGetRestau);
        getRestau.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent = RestaurantList.newIntent(getActivity(), "Dining");
                startActivity(intent);





            }
        });

        getDrinks = v.findViewById(R.id.btnGetDrinks);
        getDrinks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                Intent intent = RestaurantList.newIntent(getActivity(), "Drinks");
                startActivity(intent);





            }
        });

        getCountryFood = v.findViewById(R.id.btnGetCountryFood);
        getCountryFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(getCountryFood.getText().toString().equals(check)){

                    Toast.makeText(getActivity(), ""+ getCountryFood.getText(), Toast.LENGTH_LONG).show();




                    FragmentManager manager = getFragmentManager();
                    CountryFoodPicker dialog = new CountryFoodPicker();
                    dialog.setTargetFragment(DiningMenuFragment.this, REQUEST_COUNTRY);
                    dialog.show(manager, DIALOG_DATE);




                } else{
                    Toast.makeText(getActivity(), ""+ getCountryFood.getText(), Toast.LENGTH_LONG).show();
                    Intent intent = RestaurantList.newIntent(getActivity(), getCountryFood.getText().toString());
                    startActivity(intent);



                }

            }
        });

        getCountryFood.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                FragmentManager manager = getFragmentManager();
                CountryFoodPicker dialog = new CountryFoodPicker();
                dialog.setTargetFragment(DiningMenuFragment.this, REQUEST_COUNTRY);
                dialog.show(manager, DIALOG_DATE);

                return true;
            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return; }
        if (requestCode == REQUEST_COUNTRY) {
            String type = (String) data
                    .getSerializableExtra(CountryFoodPicker.EXTRA_COUNTRY);

            getCountryFood.setText(type);
        }
    }



}

