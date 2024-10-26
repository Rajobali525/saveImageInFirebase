package com;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savedata.R;

public class MyViewholder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;
    View v;
    public MyViewholder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_single_view);
        textView = itemView.findViewById(R.id.textView_single_view);

        v = itemView;
    }
}
