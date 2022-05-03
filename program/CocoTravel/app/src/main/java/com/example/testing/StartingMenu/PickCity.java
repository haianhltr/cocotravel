package com.example.testing.StartingMenu;



import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class PickCity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PickCityFragment();
    }
}
