package com.example.testing.StartingMenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testing.R;
import com.example.testing.globaluser;

import androidx.fragment.app.Fragment;

public class PickCityFragment extends Fragment
{
    Spinner dropdown;
TextView choose;
Button confirm;
String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mainmenu, container, false);
        choose = v.findViewById(R.id.textView);
        dropdown = v.findViewById(R.id.spinnercountryfood);
        initspinnerfooter();



        confirm = v.findViewById(R.id.button2);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {




                Intent intent = new Intent(getActivity(), City.class);
                startActivity(intent);
                globaluser.place = name;


            }
        });

        return v;
    }
    private void initspinnerfooter() {
        String[] items = new String[]{
                "Saigon", "Da Nang", "Hanoi",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));


                //choose.setText(items[position]);
                name = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }
}