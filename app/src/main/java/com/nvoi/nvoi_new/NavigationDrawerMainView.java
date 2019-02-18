package com.nvoi.nvoi_new;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.nvoi.nvoi_new.Model.User;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationDrawerMainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView image_profile;
    TextView username, email;

    DatabaseReference reference;
    FirebaseUser fuser;

    private RatingBar mRatingBar;

    StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer_main_view);      //start default drawer content
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //firebase connections
        reference = FirebaseDatabase.getInstance().getReference("Users");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);     //end default drawer content

        //create custom path

        final Button courierButton = findViewById(R.id.mainView_btnCourier);   //Courier Button
        final Button dealerButton = findViewById(R.id.mainView_btnDealer);     //Dealer Button

        final ImageButton notificationDealerButton = findViewById(R.id.notification_btnDealer);//Notification Button
        final ImageButton notificationTripButton = findViewById(R.id.btn_trips);//Notification Button

        courierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Set the path
                openTransportActivity();
            }
        });

        dealerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Add Job
                openSendActivity();
            }
        });

        notificationDealerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openNotifcationForDealer();
            }
        });

        notificationTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //to open Notifications
                openNotifcationAsTransporter();
            }
        });


        //copy from profile fragment
        image_profile = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                email.setText(user.getEmail());
                try {
                    if (user.getImageURL().equals("default")){
                        image_profile.setImageResource(R.mipmap.ic_profile_picture_round);
                    } else {
                        Glide.with(NavigationDrawerMainView.this).load(user.getImageURL()).into(image_profile);//33333
                    }
                } catch (Exception e) {
                    Toast.makeText(NavigationDrawerMainView.this, "pp : "+e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.v("PP", e.getMessage());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

        //return view;
    }

    //copy from Profile fragment
    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = this.getContentResolver();//33333
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage(){
        final ProgressDialog pd = new ProgressDialog(this); //33333
        pd.setMessage("Uploading"); //loading text while profile picture uploading
        pd.show();

        if (imageUri != null){
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }

                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("imageURL", mUri);
                        reference.updateChildren(map);

                        pd.dismiss();
                    } else {
                        Toast.makeText(getParent(), "Failed!", Toast.LENGTH_SHORT).show();//33333
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getParent(), e.getMessage(), Toast.LENGTH_SHORT).show();//33333
                    pd.dismiss();
                }
            });
        } else {
            Toast.makeText(getParent(), "No image selected", Toast.LENGTH_SHORT);//33333
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();

            if (uploadTask != null && uploadTask.isInProgress()){
                Toast.makeText(getParent(), "Upload in progress", Toast.LENGTH_SHORT).show();//33333
            } else {
                uploadImage();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(NavigationDrawerMainView.this, Login.class));
                finish();
                return true;
        }
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_chat) {
            openChatBox();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //for rating bar
    private void displayUserRatingBar(){

        mRatingBar.setVisibility(View.VISIBLE);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                reference.child("rating").setValue(rating);

                //mRatingBar.setRating(Integer.valueOf(child.getValue().toString()));
            }
        });
    }

    public void openTransportActivity() {
        Intent intent = new Intent(this, TransportActivity.class);
        startActivity(intent);
    }

    public void openSendActivity() {
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);
    }

    public void openNotifcationForDealer() {
        Intent intent = new Intent(this, NotificationsForDealer.class);
        startActivity(intent);
    }

    public void openNotifcationAsTransporter() {
        Intent intent = new Intent(this, NotificationAsTransporter.class);
        startActivity(intent);
    }

    public void openChatBox() {
        Intent intent = new Intent(this, ChatBox.class);
        startActivity(intent);
    }

}
