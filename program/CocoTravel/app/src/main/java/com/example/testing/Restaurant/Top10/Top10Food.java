package com.example.testing.Restaurant.Top10;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class Top10Food extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new Top10Fragment();
    }
}