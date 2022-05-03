package com.example.testing.Hotel.HotelListShowing;

import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class HotelListScroll extends SingleFragmentActivity {

   private static final String EXTRA_HOTEL_DISTRICT =
            "com.example.android.testing.hotel_district";
    private static final String EXTRA_HOTEL_ARRIVALDATE =
            "com.example.android.testing.hotel_arrival";
    private static final String EXTRA_ALL_SPECIFIC =
            "com.example.android.testing.hotel_check";
    public static Intent newIntent(Context packageContext, String district, String date,String check) {
        Intent intent = new Intent(packageContext, HotelListScroll.class);
        intent.putExtra(EXTRA_HOTEL_DISTRICT,district );
        intent.putExtra(EXTRA_HOTEL_ARRIVALDATE, date);
        intent.putExtra(EXTRA_ALL_SPECIFIC, check);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String district = (String) getIntent()
                .getSerializableExtra(EXTRA_HOTEL_DISTRICT);
        String date = (String) getIntent()
                .getSerializableExtra(EXTRA_HOTEL_ARRIVALDATE);
        String check = (String) getIntent()
                .getSerializableExtra(EXTRA_ALL_SPECIFIC);
        return  HotelListScrollFragment.newInstance(district,date,check);
    }


}