package com.nvoi.nvoi_new;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TransportersAdapter extends RecyclerView.Adapter<TransportersAdapter.TransportersViewHolder> {

    private Context mContext;
    private List<SuggestedTransporters> transportersList;

    public TransportersAdapter(Context mContext, List<SuggestedTransporters> transportersList) {
        this.mContext = mContext;
        this.transportersList = transportersList;
    }

    @NonNull
    @Override
    public TransportersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.transporters_list, null);
        return new TransportersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransportersViewHolder holder, int position) {
        SuggestedTransporters suggestedTransporters = transportersList.get(position);

        holder.textUserName.setText(suggestedTransporters.getName());
        holder.textDescription.setText(suggestedTransporters.getDescription());
        holder.textDescription.setText(String.valueOf(suggestedTransporters.getRating()));


//        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(suggestedTransporters.getImageUri()));
    }

    @Override
    public int getItemCount() {
        return transportersList.size();
    }

    class TransportersViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textUserName, textDescription, textRating;
        Button btnConfirm;

        public TransportersViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profile_picture);
            textUserName = itemView.findViewById(R.id.text_username);
            textDescription = itemView.findViewById(R.id.text_description);
            textRating = itemView.findViewById(R.id.text_rating);
            btnConfirm = itemView.findViewById(R.id.btn_confirm);

        }
    }


}
