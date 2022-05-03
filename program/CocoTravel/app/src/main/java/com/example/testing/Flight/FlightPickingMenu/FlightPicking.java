package com.example.testing.Flight.FlightPickingMenu;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;


public class FlightPicking extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new FlightPickingFragment();
    }
}
