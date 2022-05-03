package com.example.testing.LoginAndRegister.GetDetails;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testing.BookingDetails.BookHotel;
import com.example.testing.BookingDetails.BookingDetails;
import com.example.testing.LoginAndRegister.Login;
import com.example.testing.R;
import com.example.testing.StartingMenu.PickCity;
import com.example.testing.globaluser;

public class DashboardActivity extends AppCompatActivity {

    Button LogOut,startTrip, viewFlight,viewHotel;
    TextView EmailShow;
    String EmailHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);


        LogOut = (Button)findViewById(R.id.btnLogout);
        startTrip = (Button)findViewById(R.id.btnStartTrip);
        viewFlight = (Button)findViewById(R.id.btnViewFlight);
        viewHotel = (Button)findViewById(R.id.btnViewHotel);
        EmailShow = (TextView)findViewById(R.id.EmailShow);


        Intent intent = getIntent();
        EmailHolder = intent.getStringExtra(Login.UserEmail);
        EmailShow.setText(EmailHolder);


        startTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(DashboardActivity.this, PickCity.class);

                startActivity(intent);




            }
        });


        viewFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(DashboardActivity.this, BookingDetails.class);

                startActivity(intent);




            }
        });

        viewHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(DashboardActivity.this, BookHotel.class);

                startActivity(intent);




            }
        });


        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                globaluser.a = "";

                Intent intent = new Intent(DashboardActivity.this, Login.class);

                startActivity(intent);

                Toast.makeText(DashboardActivity.this, "Log Out Successfully", Toast.LENGTH_LONG).show();


            }
        });
    }}
