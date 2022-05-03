package com.example.testing.Restaurant.RestaurantList;

import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class RestaurantList  extends SingleFragmentActivity {
    private static final String EXTRA_RESTAU_ID =
            "com.example.android.testing.flight_id";
    public static Intent newIntent(Context packageContext, String type) {
        Intent intent = new Intent(packageContext, RestaurantList.class);
        intent.putExtra(EXTRA_RESTAU_ID,type);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String type = (String) getIntent()
                .getSerializableExtra(EXTRA_RESTAU_ID);
        return RestaurantListFragment.newInstance(type);
    }


}