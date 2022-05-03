package com.example.testing.Flight.SpecificFlight;

import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;


public class FlightActivity extends SingleFragmentActivity {
    private static final String EXTRA_FLIGHT_ID =
            "com.example.android.testing.flight_id";
    public static Intent newIntent(Context packageContext, String flightId) {
        Intent intent = new Intent(packageContext, FlightActivity.class);
        intent.putExtra(EXTRA_FLIGHT_ID, flightId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String flightId = (String) getIntent()
                .getSerializableExtra(EXTRA_FLIGHT_ID);
        return FlightFragment.newInstance(flightId);
    }
}