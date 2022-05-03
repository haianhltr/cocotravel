package com.example.testing.Flight.FlightListShowing;

import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class FlightList extends SingleFragmentActivity {

    private static final String EXTRA_FLIGHT_FROM =
            "com.example.android.testing.flight_from";
    private static final String EXTRA_FLIGHT_TO =
            "com.example.android.testing.flight_to";
    private static final String EXTRA_FLIGHT_DATE =
            "com.example.android.testing.flight_date";
    private static final String EXTRA_ALL_SPECIFIC =
            "com.example.android.testing.flight_check";

    public static Intent newIntent(Context packageContext, String from, String to, String date,String check) {
        Intent intent = new Intent(packageContext, FlightList.class);
        intent.putExtra(EXTRA_FLIGHT_FROM, from);
        intent.putExtra(EXTRA_FLIGHT_TO, to);
        intent.putExtra(EXTRA_FLIGHT_DATE, date);
        intent.putExtra(EXTRA_ALL_SPECIFIC, check);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String from = (String) getIntent()
                .getSerializableExtra(EXTRA_FLIGHT_FROM);
        String to = (String) getIntent()
                .getSerializableExtra(EXTRA_FLIGHT_TO);
        String date = (String) getIntent()
                .getSerializableExtra(EXTRA_FLIGHT_DATE);
        String check = (String) getIntent()
                .getSerializableExtra(EXTRA_ALL_SPECIFIC);
        return FlightListFragment.newInstance(from,to,date,check);
    }


}