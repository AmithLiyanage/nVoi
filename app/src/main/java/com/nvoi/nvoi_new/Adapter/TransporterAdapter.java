package com.nvoi.nvoi_new.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nvoi.nvoi_new.Model.Transporter;
import com.nvoi.nvoi_new.R;

import java.util.List;

public class TransporterAdapter extends RecyclerView.Adapter<TransporterAdapter.ViewHolder> {

    private Context mContext;
    private List<Transporter> mTransporters;

    public TransporterAdapter(Context mContext, List<Transporter> mTransporters){
        this.mTransporters = mTransporters;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TransporterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transporter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transporter transporter = mTransporters.get(position);

        holder.username.setText(transporter.getUsername());
//        if ( holder.description.equals("")) {
            holder.description.setText("No description about deal.........................");
//        } else {
//            holder.description.setText(transporter.getDescription());
//        }
        holder.t_rating.setText(String.valueOf(transporter.getRating()));
        //if (transporter.getImageURL().equals("default")) {
            holder.transporter_pro_pic.setImageResource(R.mipmap.ic_profile_picture_round);
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

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            username = itemView.findViewById(R.id.username);
            description = itemView.findViewById(R.id.description);
            t_rating = itemView.findViewById(R.id.txt_rating);
            transporter_pro_pic = itemView.findViewById(R.id.transporter_profile_picture);
            //commit = itemView.findViewById(R.id.btn_commit);
        }

        @Override
        public void onClick(View v) {
            
        }
    }

}
