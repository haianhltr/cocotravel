package com.example.testing.Hotel.HotelPickingMenu;

import com.example.testing.Hotel.HotelPickingMenu.HotelPickingFragment;
import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class HotelPicking extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HotelPickingFragment();
    }
}