package com.example.testing.others.otherspicking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.testing.R;
import com.example.testing.others.otherslist.OthersList;

import androidx.fragment.app.Fragment;

public class OthersPickListFragment extends Fragment {
    private Button mbuttonLaundry, mbuttonCyber, mbuttonBars, mbuttonConvi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pickothers, container, false);





        mbuttonLaundry= (Button) v.findViewById(R.id.btnLaundry);
        mbuttonLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = OthersList.newIntent(getActivity(), "Laundry");
                startActivity(intent);
            }
        });


        mbuttonCyber= (Button) v.findViewById(R.id.btnCyber);
        mbuttonCyber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = OthersList.newIntent(getActivity(), "Gaming");
                startActivity(intent);
            }
        });
        mbuttonConvi= (Button) v.findViewById(R.id.btnConvi);
        mbuttonConvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = OthersList.newIntent(getActivity(), "Convenience store");
                startActivity(intent);
            }
        });





        mbuttonBars= (Button) v.findViewById(R.id.btnBars);
        mbuttonBars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = OthersList.newIntent(getActivity(), "Bars");
                startActivity(intent);
            }
        });


        return v;

    }


}