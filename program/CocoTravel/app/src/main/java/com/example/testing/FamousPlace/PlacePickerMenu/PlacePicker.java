package com.example.testing.FamousPlace.PlacePickerMenu;

import com.example.testing.FamousPlace.PlacePickerMenu.PlacePickerFragment;
import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class PlacePicker  extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new PlacePickerFragment();
    }
}