package com.example.testing.FamousPlace.PlacePickerMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.testing.FamousPlace.PlaceList.PageList;
import com.example.testing.R;

import androidx.fragment.app.Fragment;

public class PlacePickerFragment extends Fragment {
    private Button mbuttonFlight, mbuttonHotel, mbuttonPlace;
    private ImageView mImage1,mImage2,mImage3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pickplace, container, false);


        mImage1= (ImageView) v.findViewById(R.id.imgTop10);
        mImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = PageList.newIntent(getActivity(), "Top10");
                startActivity(intent);
            }
        });

        mImage2= (ImageView) v.findViewById(R.id.imgSGS);
        mImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = PageList.newIntent(getActivity(), "SGS");
                startActivity(intent);
            }
        });


        return v;

    }


}