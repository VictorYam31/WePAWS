package com.wepaws.wepaws;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HomeHolder extends RecyclerView.ViewHolder{

    RelativeLayout homeRelativeLayout;
    ImageView homeItemImageView;
    TextView homeItemName;

    public HomeHolder (View itemView) {
        super(itemView);
        this.homeRelativeLayout = itemView.findViewById(R.id.home_item_relative_layout);
        this.homeItemImageView = itemView.findViewById((R.id.home_item_image));
        this.homeItemName = itemView.findViewById((R.id.home_item_name));
    }

}
