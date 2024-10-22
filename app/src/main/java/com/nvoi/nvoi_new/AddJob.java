package com.nvoi.nvoi_new;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

    private EditText description,weight,time,date;
    private Spinner packageType,type;

//    private String time="12/12/2018", date="13.45",;

    private String dummyTransporter = "JgWQj3xdGMZIrxIzsfXKr6MEBES2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);


        final Button btnConfirm =  findViewById(R.id.button);

        description=(EditText)findViewById(R.id.description);
        weight=(EditText)findViewById(R.id.weight);
        time=(EditText)findViewById(R.id.time);
        date=(EditText)findViewById(R.id.date);

        packageType=(Spinner) findViewById(R.id.pack_type);
        type=(Spinner) findViewById(R.id.ownerType);

        ArrayAdapter<String> pakType=new ArrayAdapter<String>(AddJob.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.packType));
        ArrayAdapter<String> ownType=new ArrayAdapter<String>(AddJob.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.ownerType));

        pakType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        packageType.setAdapter(pakType);
        ownType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(ownType);



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
        String package_Type=packageType.getSelectedItem().toString();
//        String typeOwner=type.getText().toString();
        String timeDs=time.getText().toString();
        String dateDs=date.getText().toString();
        float weightPack=Float.parseFloat(weight.getText().toString());

        if(weightPack>50.0 && weightPack<0.0){
            Toast.makeText(AddJob.this,"The weight must be less than 50kg",Toast.LENGTH_SHORT).show();
        }else if(dateDs.length() != 10){
            Toast.makeText(AddJob.this,"Date is not correct",Toast.LENGTH_SHORT).show();
        }else{
            String ownerId=FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Job").child(ownerId);

            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("ownerId",ownerId);
            hashMap.put("transporter","None");
//            hashMap.put("ownerType",typeOwner);
            hashMap.put("startLatitude",start_lat);
            hashMap.put("startLongitude",start_lng);
            hashMap.put("endLatitude",end_lat);
            hashMap.put("endLongitude",end_lng);
            hashMap.put("partnerId","None");
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
