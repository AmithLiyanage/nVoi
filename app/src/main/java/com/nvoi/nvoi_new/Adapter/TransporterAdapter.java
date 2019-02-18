package com.nvoi.nvoi_new.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nvoi.nvoi_new.Model.Transporter;
import com.nvoi.nvoi_new.Model.User;
import com.nvoi.nvoi_new.NavigationDrawerMainView;
import com.nvoi.nvoi_new.NotificationAsTransporter;
import com.nvoi.nvoi_new.NotificationsForDealer;
import com.nvoi.nvoi_new.R;
import com.nvoi.nvoi_new.ViewProfile;

import java.util.List;

public class TransporterAdapter extends RecyclerView.Adapter<TransporterAdapter.ViewHolder> {

    private Context ctx;
    private List<Transporter> mTransporters;

    public TransporterAdapter(Context ctx, List<Transporter> mTransporters){
        this.mTransporters = mTransporters;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TransporterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transporter_item, parent, false);
        return new ViewHolder(view,ctx, mTransporters);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Transporter transporter = mTransporters.get(position);
        holder.username.setText(transporter.getUsername());
        holder.description.setText("No description about deal.........................");
        holder.t_rating.setText(String.valueOf(transporter.getRating()));
        holder.transporter_pro_pic.setImageResource(R.mipmap.ic_profile_picture_round);

//        if ( holder.description.equals("")) {
//            holder.description.setText("No description about deal.........................");
//        } else {
//            holder.description.setText(transporter.getDescription());
//        }
//        holder.t_rating.setText(String.valueOf(transporter.getRating()));
//        if (transporter.getImageURL().equals("default")) {
//            holder.transporter_pro_pic.setImageResource(R.mipmap.ic_profile_picture_round);
//        } else {
//            Glide.with(mContext).load(transporter.getImageURL()).into(holder.transporter_pro_pic);
//        }

    }

    @Override
    public int getItemCount() {
        return mTransporters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView username, description, t_rating;
        public ImageView transporter_pro_pic;
        public Button commit;
        Context ctx;
        List<Transporter> mTransporters;

        public ViewHolder(View itemView, Context ctx, List<Transporter> mTransporters) {
            super(itemView);
            this.ctx = ctx;
            this.mTransporters = mTransporters;

            itemView.setOnClickListener(this);

            username = itemView.findViewById(R.id.username);
            description = itemView.findViewById(R.id.description);
            t_rating = itemView.findViewById(R.id.txt_rating);
            transporter_pro_pic = itemView.findViewById(R.id.transporter_profile_picture);
            commit = itemView.findViewById(R.id.btn_commit);
        }

        @Override
        public void onClick(View v) {
           Toast.makeText(v.getContext(), "pyy : ", Toast.LENGTH_LONG).show();

            int position = getAdapterPosition();
            Transporter transporter = this.mTransporters.get(position);
            Intent intent = new Intent(v.getContext(), ViewProfile.class);

            intent.putExtra("id", transporter.getId());
            intent.putExtra("username", transporter.getUsername());
            intent.putExtra("description", transporter.getDescription());
            intent.putExtra("rating", transporter.getRating());
            intent.putExtra("imageURL", transporter.getImageURL());
            this.ctx.startActivity(intent);


            //username.setText(transporter.getUsername());
            //email.setText(transporter.getEmail());
//            try {
//                if (transporter.getImageURL().equals("default")){
//                    image_profile.setImageResource(R.mipmap.ic_profile_picture_round);
//                } else {
//                    Glide.with(NavigationDrawerMainView.this).load(user.getImageURL()).into(image_profile);//33333
//                }
//            } catch (Exception e) {
//                Toast.makeText(NavigationDrawerMainView.this, "pp : "+e.getMessage(), Toast.LENGTH_LONG).show();
//                Log.v("PP", e.getMessage());
//            }

        }
    }

}
