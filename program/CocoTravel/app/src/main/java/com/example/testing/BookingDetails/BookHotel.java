package com.example.testing.BookingDetails;

import com.example.testing.BookingDetails.BookingDetailsFragment.BookingHotelFragment;
import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class BookHotel  extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BookingHotelFragment();
    }
}