package com.example.testing.others.otherspicking;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class OthersPickList extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new OthersPickListFragment();
    }

}

