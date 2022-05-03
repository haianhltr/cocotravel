package com.example.testing.others.otherslist;

import android.content.Context;
import android.content.Intent;

import com.example.testing.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class OthersList extends SingleFragmentActivity {

    private static final String EXTRA_OTHERS_ID =
            "com.example.android.testing.others_id";
    public static Intent newIntent(Context packageContext, String type) {
        Intent intent = new Intent(packageContext, OthersList.class);
        intent.putExtra(EXTRA_OTHERS_ID,type);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String type = (String) getIntent()
                .getSerializableExtra(EXTRA_OTHERS_ID);
        return OtherListFragment.newInstance(type);
    }

}