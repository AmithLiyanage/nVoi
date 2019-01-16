package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nvoi.nvoi_new.Adapter.TransporterAdapter;
import com.nvoi.nvoi_new.Model.Transporter;
import com.nvoi.nvoi_new.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotificationsForDealer extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransporterAdapter transporterAdapter;
    private List<Transporter> mTransporters;

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

        recyclerView = findViewById(R.id.transporters_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));//getContext

        mTransporters = new ArrayList<>();

        readTransporters();

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

    private void readTransporters() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference referenceUsers = FirebaseDatabase.getInstance().getReference("Users");
        //final DatabaseReference referenceTransporter = FirebaseDatabase.getInstance().getReference("Trips").child("transporterId");

        referenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTransporters.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    Transporter transporter = snapshot.getValue(Transporter.class);

                    //condition
                    assert user != null;
                    assert firebaseUser != null;
                    if (user.getId().equals("JgWQj3xdGMZIrxIzsfXKr6MEBES2")){   //referenceTransporter

                        user.getUsername();
                        user.getImageURL();
                        user.getRating();

                        HashMap<String, Object> hashMap = new HashMap<>();

                        transporter.setUsername(user.getUsername());
                        transporter.setDescription("Empty");
                        transporter.setImageURL(user.getImageURL());
                        transporter.setRating(user.getRating());

                        mTransporters.add(transporter);
                    }

                }

                transporterAdapter = new TransporterAdapter(getParent(), mTransporters);
                recyclerView.setAdapter(transporterAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
