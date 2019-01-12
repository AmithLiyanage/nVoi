package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationsForDealer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_for_dealer);

        final Button viewButton1 =  findViewById(R.id.notifications_btnViewProfile_1);     //View Courier Profile Button
        final Button confirmButton1 =  findViewById(R.id.notifications_btnConfirmCourior_1);     //Confirm Courier Profile Button
        final Button viewButton2 =  findViewById(R.id.notifications_btnViewProfile_2);     //View Courier Profile Button
        final Button confirmButton2 =  findViewById(R.id.notifications_btnConfirmCourior_2);     //Confirm Courier Profile Button
        final Button viewButton3 =  findViewById(R.id.notifications_btnViewProfile_3);     //View Courier Profile Button
        final Button confirmButton3 =  findViewById(R.id.notifications_btnConfirmCourior_3);     //Confirm Courier Profile Button
        final Button viewButton4 =  findViewById(R.id.notifications_btnViewProfile_4);     //View Courier Profile Button
        final Button confirmButton4 =  findViewById(R.id.notifications_btnConfirmCourior_4);     //Confirm Courier Profile Button
        final Button viewButton5 =  findViewById(R.id.notifications_btnViewProfile_5);     //View Courier Profile Button
        final Button confirmButton5 =  findViewById(R.id.notifications_btnConfirmCourior_5);     //Confirm Courier Profile Button
        final Button viewButton6 =  findViewById(R.id.notifications_btnViewProfile_6);     //View Courier Profile Button
        final Button confirmButton6 =  findViewById(R.id.notifications_btnConfirmCourior_6);     //Confirm Courier Profile Button


        viewButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openCourierProfile();
            }
        });

        confirmButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                confirmCourier();
            }
        });

        viewButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openCourierProfile();
            }
        });

        confirmButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                confirmCourier();
            }
        });

        viewButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openCourierProfile();
            }
        });

        confirmButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                confirmCourier();
            }
        });

        viewButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openCourierProfile();
            }
        });

        confirmButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                confirmCourier();
            }
        });

        viewButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openCourierProfile();
            }
        });

        confirmButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                confirmCourier();
            }
        });

        viewButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openCourierProfile();
            }
        });

        confirmButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                confirmCourier();
            }
        });
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
