package com.example.testing.StartingMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testing.FamousPlace.PlacePickerMenu.PlacePicker;
import com.example.testing.Flight.FlightPickingMenu.FlightPicking;
import com.example.testing.Hotel.HotelPickingMenu.HotelPicking;
import com.example.testing.R;
import com.example.testing.Restaurant.Menu.DiningMenu;
import com.example.testing.others.otherspicking.OthersPickList;

import androidx.fragment.app.Fragment;

public class CityFragment extends Fragment {
    private Button mbuttonFlight, mbuttonHotel, mbuttonPlace, mbuttonDining,mbuttonOthers;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.citymainmenu, container, false);


        mbuttonHotel = (Button) v.findViewById(R.id.button5);
        mbuttonFlight = (Button) v.findViewById(R.id.button4);
        mbuttonFlight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FlightPicking.class);
                startActivity(intent);



            }

        });

        mbuttonHotel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HotelPicking.class);
                startActivity(intent);



            }

        });


        mbuttonDining = (Button) v.findViewById(R.id.button6);
        mbuttonDining.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiningMenu.class);
                startActivity(intent);



            }

        });

      mbuttonPlace = (Button) v.findViewById(R.id.button7);
        mbuttonPlace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlacePicker.class);
                startActivity(intent);



            }

        });
        mbuttonOthers = (Button) v.findViewById(R.id.button8);
        mbuttonOthers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OthersPickList.class);
                startActivity(intent);



            }

        });

        return v;

    }


}