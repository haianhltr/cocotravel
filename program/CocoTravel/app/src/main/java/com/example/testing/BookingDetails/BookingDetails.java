package com.example.testing.BookingDetails;

import com.example.testing.BookingDetails.BookingDetailsFragment.BookingDetailsFragment;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class BookingDetails extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BookingDetailsFragment();
    }
}