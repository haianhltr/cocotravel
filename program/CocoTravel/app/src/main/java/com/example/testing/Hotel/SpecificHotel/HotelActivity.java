package com.example.testing.Hotel.SpecificHotel;

import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class HotelActivity extends SingleFragmentActivity {
    private static final String EXTRA_HOTEL_ID =
            "com.example.android.testing.HOTEL_id";
    private static final String EXTRA_HOTEL_ARRIVALDATE =
            "com.example.android.testing.HOTEL_ARRIVALDATE";
    public static Intent newIntent(Context packageContext, String hotelId,String date) {
        Intent intent = new Intent(packageContext, HotelActivity.class);
        intent.putExtra(EXTRA_HOTEL_ID, hotelId);
        intent.putExtra(EXTRA_HOTEL_ARRIVALDATE, date);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String hotelId = (String) getIntent()
                .getSerializableExtra(EXTRA_HOTEL_ID);
        String date = (String) getIntent()
                .getSerializableExtra(EXTRA_HOTEL_ARRIVALDATE);
        return HotelFragment.newInstance(hotelId,date);
    }
}