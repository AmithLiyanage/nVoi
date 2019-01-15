package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class NotificationsForDealer extends AppCompatActivity {

    RecyclerView recyclerView;
    TransportersAdapter adapter;
    List<SuggestedTransporters> transportersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_for_dealer);

        Toolbar toolbar = findViewById((R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("NOTIFICATION");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationsForDealer.this, NavigationDrawerMainView.class).setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP));
            }
        });

        transportersList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);//fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new TransportersAdapter(this, transportersList);
        recyclerView.setAdapter(adapter);

//        final Button viewButton1 =  findViewById(R.id.notifications_btnViewProfile_1);     //View Courier Profile Button
//        final Button confirmButton1 =  findViewById(R.id.notifications_btnConfirmCourior_1);     //Confirm Courier Profile Button
//        final Button viewButton2 =  findViewById(R.id.notifications_btnViewProfile_2);     //View Courier Profile Button
//        final Button confirmButton2 =  findViewById(R.id.notifications_btnConfirmCourior_2);     //Confirm Courier Profile Button
//
//
//        viewButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { //to open Transporter profile
//                openCourierProfile();
//            }
//        });
//
//        confirmButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { //to open Notifications
//                confirmCourier();
//            }
//        });
//
//        viewButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { //to open Transporter profile
//                openCourierProfile();
//            }
//        });
//
//        confirmButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { //to open Notifications
//                confirmCourier();
//            }
//        });

    }

    public void openCourierProfile() {
        Intent intent = new Intent(this, CouriorProfile.class);
        startActivity(intent);
    }

    public void confirmCourier() {
        Intent intent = new Intent(this, NavigationDrawerMainView.class);
        startActivity(intent);
    }

}
