package com.example.testing.Restaurant.Menu;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testing.R;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CountryFoodPicker extends DialogFragment {
    Spinner dropdown;
    TextView choose;
    public static final String EXTRA_COUNTRY =
            "com.example.tessting.date";


    @Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.countryfoodpicker, null);
        choose = v.findViewById(R.id.txtShowCountryFood);
        dropdown = v.findViewById(R.id.spinnercountryfood);
        initspinnerfooter();



        return new AlertDialog.Builder(getActivity())
            .setView(v)
.setTitle("Please choose your favorite country food,long press to change country.")
        .setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String country = choose.getText().toString();
                        sendResult(Activity.RESULT_OK, country);
                    }})




        .create();
        }

    private void sendResult(int resultCode, String type) {
        if (getTargetFragment() == null) {
            return; }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_COUNTRY, type);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }



    private void initspinnerfooter() {
        String[] items = new String[]{
                "China", "Greek", "Mexico",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));

                choose.setText(items[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }



        }