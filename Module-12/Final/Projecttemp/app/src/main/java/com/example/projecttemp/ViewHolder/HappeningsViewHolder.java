package com.example.projecttemp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.projecttemp.R;

public class HappeningsViewHolder extends RecyclerView.ViewHolder {

    public TextView eventName, date, description;


    public HappeningsViewHolder(@NonNull View itemView) {
        super(itemView);

        eventName = (TextView) itemView.findViewById(R.id.event_name);
        date = (TextView) itemView.findViewById(R.id.event_date);
        description = (TextView) itemView.findViewById(R.id.event_desc);
    }
}
