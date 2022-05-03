package com.example.testing.Flight.FlightPickingMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testing.Flight.FlightListShowing.FlightList;
import com.example.testing.R;
import com.example.testing.globaluser;

import java.text.SimpleDateFormat;

import androidx.fragment.app.Fragment;


public class FlightPickingFragment extends Fragment {
    private Button mbutton;
    private TextView mText;
    private Button mbuttonShowAll;
    private DatePicker picker;
    private EditText mFrom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.datepickerflight, container, false);

        mbutton = (Button) v.findViewById(R.id.btnConfirmHotel);
        picker=(DatePicker)v.findViewById(R.id.dialog_date_picker);
        mbuttonShowAll=(Button) v.findViewById(R.id.btnShowAllHotel);
        mFrom = (EditText) v.findViewById(R.id.txtEnterPerson);

        //mbuttonShowAll.setText(globaluser.place);
        mbuttonShowAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = FlightList.newIntent(getActivity(), mFrom.getText().toString(),globaluser.place,"2021-04-26", "All" );
                startActivity(intent);

            }

                                          });

        mbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy" );

                int month = picker.getMonth() +  1 ;
                if (month < 10){

                   // String a = "0" + month  + "/" + picker.getDayOfMonth() + "/"+ picker.getYear();
                    String a =  picker.getYear() + "-" + "0" + month  + "-" + picker.getDayOfMonth();
                    Intent intent = FlightList.newIntent(getActivity(), mFrom.getText().toString(),globaluser.place,a,"Specific" );
                    startActivity(intent);
                }else
                {

                    String a =  picker.getYear() + "-"  + month  + "-" + picker.getDayOfMonth();
                   // String a = month  + "/" + picker.getDayOfMonth() + "/"+ picker.getYear();
                    mText.setText(a);
                    Intent intent = FlightList.newIntent(getActivity(), mFrom.getText().toString(),globaluser.place,a, "Specific" );
                    startActivity(intent);
                }






                //Intent intent = new Intent(getActivity(), FlightList.class);
                //intent.putExtra("FROM", mFrom.getText());

               // mText.setText("Selected Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());

            }

        });

        return v;

    }


}
