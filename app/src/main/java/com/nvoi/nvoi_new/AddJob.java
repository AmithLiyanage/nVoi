package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddJob extends AppCompatActivity {

//    private String startLocation="1", endLocation="2";

    private EditText description,weight,type,packageType,time,date;

//    private String time="12/12/2018", date="13.45",;

    private String dummyTransporter = "JgWQj3xdGMZIrxIzsfXKr6MEBES2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        Spinner spinner;
        String paths[]={"A","B","C"};


        final Button btnConfirm =  findViewById(R.id.button);

        description=(EditText)findViewById(R.id.description);
        weight=(EditText)findViewById(R.id.weight);
        packageType=(EditText)findViewById(R.id.pack_type);
        type=(EditText)findViewById(R.id.ownerType);
        time=(EditText)findViewById(R.id.time);
        date=(EditText)findViewById(R.id.date);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDealToDB();
            }
        });
    }

    private void sendDealToDB() {
        //getting the passed data
        Bundle bundle_getPassData=getIntent().getExtras();

        Double start_lat=bundle_getPassData.getDouble("start_lat");
        Double start_lng=bundle_getPassData.getDouble("start_lng");

        Double end_lat=bundle_getPassData.getDouble("end_lat");
        Double end_lng=bundle_getPassData.getDouble("end_lng");

        String descriptionT=description.getText().toString();
        String package_Type=packageType.getText().toString();
        String typeOwner=type.getText().toString();
        String timeDs=time.getText().toString();
        String dateDs=date.getText().toString();
        float weightPack=Float.parseFloat(weight.getText().toString());

        if(weightPack>50.0 && weightPack>0){
            Toast.makeText(AddJob.this,"The weight must be less than 50kg",Toast.LENGTH_SHORT).show();
        }else if(dateDs.length() != 10){
            Toast.makeText(AddJob.this,"Date is not correct",Toast.LENGTH_SHORT).show();
        }else{
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Job").child("Owner");

            String ownerId=FirebaseAuth.getInstance().getCurrentUser().getUid();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ownerId",ownerId);
            hashMap.put("transporter",dummyTransporter);
            hashMap.put("ownerType",typeOwner);
            hashMap.put("startLatitude",start_lat);
            hashMap.put("startLongitude",start_lng);
            hashMap.put("endLatitude",end_lat);
            hashMap.put("endLongitude",end_lng);
            hashMap.put("otherPerson","");
            hashMap.put("time",timeDs);
            hashMap.put("date",dateDs);
            hashMap.put("description",descriptionT);
            hashMap.put("packageType",package_Type);

            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Intent intent = new Intent(AddJob.this, NavigationDrawerMainView.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(AddJob.this, "Data recorded Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });

        }

    }

}
