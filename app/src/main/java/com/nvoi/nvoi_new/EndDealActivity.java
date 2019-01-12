package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nvoi.nvoi_new.Model.User;

import java.util.HashMap;

public class EndDealActivity extends AppCompatActivity {

    DatabaseReference reference;

    private String dummyUser ="JgWQj3xdGMZIrxIzsfXKr6MEBES2";

    private RatingBar ratingBar;

    Button btnBad, btnGood, btnConfirm;

    private int goodOrBad = 0;
    private int noOfTransport;
    private double newRating, oldRating, value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_deal);

        Toolbar toolbar = findViewById((R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("END DEAL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EndDealActivity.this, ChatBox.class).setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP));
            }
        });

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        btnBad = (Button)findViewById(R.id.rating_bad);
        btnGood = (Button)findViewById(R.id.rating_good);
        btnConfirm = (Button)findViewById(R.id.confirm_rating);

        btnBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodOrBad = -1;
            }
        });

        btnGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodOrBad = 1;
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                newRating = (int)rating;
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRating();

                startActivity(new Intent(EndDealActivity.this, NavigationDrawerMainView.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.end_deal:
                startActivity(new Intent(this, EndDealActivity.class));
                finish();
                return true;
        }
        return false;
    }

    public void calculateRating(){

        reference = FirebaseDatabase.getInstance().getReference("Users").child(dummyUser);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                oldRating = user.getRating();

                Toast.makeText(EndDealActivity.this, "old --> "+oldRating, Toast.LENGTH_LONG).show();
                noOfTransport = user.getNoOfTransport();

                if (goodOrBad == 0){
                    Toast.makeText(EndDealActivity.this, "Please mark rating Good or Bad", Toast.LENGTH_SHORT).show();
                } else {
                    value = (oldRating * (noOfTransport +1) + newRating * goodOrBad) / (noOfTransport + 2);// Rating Successful
                    //Toast.makeText(EndDealActivity.this, "oldRating = "+oldRating+"value = "+value+", noOfTransport = "+noOfTransport+", newRating = "+newRating, Toast.LENGTH_LONG).show();
                    Toast.makeText(EndDealActivity.this, "Rating Submited !", Toast.LENGTH_SHORT).show();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("rating", value);
                    hashMap.put("noOfTransport", noOfTransport+1);

                    reference.updateChildren(hashMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
