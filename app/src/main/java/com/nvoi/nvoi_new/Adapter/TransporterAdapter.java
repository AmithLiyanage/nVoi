package com.nvoi.nvoi_new.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.transporter_item, null);
        return new ViewHolder(view);
//        View view = LayoutInflater.from(mContext).inflate(R.layout.transporter_item, parent, false);
//        return new TransporterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transporter transporter = mTransporters.get(position);

        holder.username.setText(transporter.getUsername());
        holder.description.setText(transporter.getDescription());
        holder.rating.setText(String.valueOf(transporter.getRating()));
        if (transporter.getImageURL().equals("default")) {
            holder.transporter_pro_pic.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(transporter.getImageURL()).into(holder.transporter_pro_pic);
        }
    }

    @Override
    public int getItemCount() {
        return mTransporters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username, description, rating;
        public ImageView transporter_pro_pic;

        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            description = itemView.findViewById(R.id.description);
            rating = itemView.findViewById(R.id.rating);
            transporter_pro_pic = itemView.findViewById(R.id.transporter_profile_picture);
        }
    }

}
