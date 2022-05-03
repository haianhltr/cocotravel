package com.example.testing.Restaurant.Menu;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class DiningMenu extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DiningMenuFragment();
    }

}

