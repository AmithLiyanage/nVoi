package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Toolbar toolbar = findViewById((R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VIEW PROFILE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfile.this, NotificationsForDealer.class).setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP));
            }
        });
    }
}
