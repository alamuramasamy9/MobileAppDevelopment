package edu.northeastern.numadsp23_alamuramasamy;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewItem extends RecyclerView.ViewHolder implements ItemInterface {
    public TextView url;
    public TextView name;
    public ImageView edit;
    public ImageView delete;


    public ViewItem(View itemView, ItemInterface urlLink) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        url = itemView.findViewById(R.id.url);
        edit = itemView.findViewById(R.id.edit);

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(urlLink!=null) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        urlLink.onItemClick(position);
                    }
                }
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(urlLink!=null) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        urlLink.onItemClick(position);
                    }
                }
            }
        });

    }


    @Override
    public void onItemClick(int position) {

    }
}