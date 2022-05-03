package com.example.testing.FamousPlace.PlaceList;



import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class PageList extends SingleFragmentActivity {

    private static final String EXTRA_PLACE_ID =
            "com.example.android.testing.place_id";
    public static Intent newIntent(Context packageContext, String type) {
        Intent intent = new Intent(packageContext, PageList.class);
        intent.putExtra(EXTRA_PLACE_ID,type);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String type = (String) getIntent()
                .getSerializableExtra(EXTRA_PLACE_ID);
        return PlaceListFragment.newInstance(type);
    }

}