package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfile extends AppCompatActivity {

    CircleImageView image_profile;
    TextView username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "pp : ", Toast.LENGTH_LONG).show();
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

//        image_profile = findViewById(R.id.profile_image);
        username = (TextView) findViewById(R.id.username);
//        email = (TextView) findViewById(R.id.email);

        username.setText(getIntent().getStringExtra("username"));
        //email.setText(user.getEmail());
    }
}
