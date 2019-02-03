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
import android.widget.Toast;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTransporters = new ArrayList<>();

        try {
            readTransporters();
        } catch (Exception e) {
            Toast.makeText(this, "error read : "+e, Toast.LENGTH_LONG ).show();
        }

        transporterAdapter = new TransporterAdapter(getParent(), mTransporters);
        recyclerView.setAdapter(transporterAdapter);
    }

    private void readTransporters() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Transporterdetails");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTransporters.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Transporter transporter = snapshot.getValue(Transporter.class);

                    assert transporter != null;
                    assert firebaseUser != null;
                    if (!transporter.getId().equals(firebaseUser.getUid())) { //choose all users without logged user
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
}
