package com.example.testing.StartingMenu;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class City
    extends SingleFragmentActivity {

        @Override
        protected Fragment createFragment() {
            return new CityFragment();
        }
    }

