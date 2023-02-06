package edu.northeastern.numadsp23_alamuramasamy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ViewItem>{
    Context context;
    private static ArrayList<URL> list = null;
    private ItemInterface itemUrl;

    private ImageView edit;

    public ItemAdapter(ArrayList<URL> itemList, Context context, ItemInterface linkClick) {
        this.list = itemList;
        this.context = context;
        this.itemUrl = linkClick;
        System.out.println("bvnn"+itemUrl);
    }

    @Override
    public ViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_card, parent,false);
        return new ViewItem(view, itemUrl);


    }

    @Override
    public void onBindViewHolder(ViewItem holder, int position) {
        URL currentItem = list.get(position);
        holder.name.setText(currentItem.getName());
        holder.url.setText(currentItem.getUrl());

    }



//     edit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        url = urlIp.getText().toString();
//                        name = nameIp.getText().toString();
//                        a.setName(name);
//                        a.setUrl(url);
//                        System.out.println("edited");
//                        //linkStore.add(new URL(url, name));
//                    }
//                });

    @Override
    public int getItemCount() {
        return list.size();
    }


}