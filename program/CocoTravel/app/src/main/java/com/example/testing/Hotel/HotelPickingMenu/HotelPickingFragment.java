package com.example.testing.Hotel.HotelPickingMenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testing.Hotel.HotelListShowing.HotelListScroll;
import com.example.testing.R;

import java.text.SimpleDateFormat;

import androidx.fragment.app.Fragment;

public class HotelPickingFragment extends Fragment {
    private Button mbutton;
    private TextView mText;
    Spinner dropdown;
    private Button mbuttonShowAll;
    private DatePicker picker;
    private EditText mFrom;
    private String district;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.datepickerhotel, container, false);
        dropdown = v.findViewById(R.id.spinnerHotel);
        initspinnerfooter();


        picker=(DatePicker)v.findViewById(R.id.dialog_date_picker);

        mbuttonShowAll = (Button) v.findViewById(R.id.btnShowAllHotel);
        mbuttonShowAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy" );

                int month = picker.getMonth() +  1 ;
                if (month < 10){

                    String a =  picker.getYear() + "-" + "0" + month  + "-" + picker.getDayOfMonth();

                    Intent intent = HotelListScroll.newIntent(getActivity(),district,a,"All" );
                    startActivity(intent);
                }else
                {


                    String a =  picker.getYear() + "-"  + month  + "-" + picker.getDayOfMonth();
                    mText.setText(a);
                    Intent intent = HotelListScroll.newIntent(getActivity(), district,a,"All" );
                    startActivity(intent);
                }




                // Intent intent = new Intent(getActivity(), HotelListScroll.class);
                //startActivity(intent);

            }

        });



        mbutton = (Button) v.findViewById(R.id.btnConfirmHotel);
        mbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy" );

                int month = picker.getMonth() +  1 ;
                if (month < 10){

                    String a =  picker.getYear() + "-" + "0" + month  + "-" + picker.getDayOfMonth();

                    Intent intent = HotelListScroll.newIntent(getActivity(),district,a,"Specific" );
                    startActivity(intent);
                }else
                {


                    String a =  picker.getYear() + "-"  + month  + "-" + picker.getDayOfMonth();
                    mText.setText(a);
                    Intent intent = HotelListScroll.newIntent(getActivity(), district,a,"Specific" );
                    startActivity(intent);
                }




               // Intent intent = new Intent(getActivity(), HotelListScroll.class);
                //startActivity(intent);

            }

        });

        return v;

    }

    private void initspinnerfooter() {
        String[] items = new String[]{
                "1", "2", "3","4","5","6","7","8","9","10","11","12","Binh Thanh","Phu Nhuan","Go Vap","Tan Binh"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));



district = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}
