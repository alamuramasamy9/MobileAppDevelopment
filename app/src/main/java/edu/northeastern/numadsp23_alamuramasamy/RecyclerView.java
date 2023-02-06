package edu.northeastern.numadsp23_alamuramasamy;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerView extends AppCompatActivity implements ItemInterface {

    String url, name;
    androidx.recyclerview.widget.RecyclerView linkRecyclerView;
    ItemAdapter linkAdapter;
    ArrayList<URL> linkStore;
    FloatingActionButton floatingActionButton;
    ConstraintLayout newLayout;
    Button edit;
    URL a;

    private Button delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        linkStore = new ArrayList<>();


        linkRecyclerView = findViewById(R.id.url_recycler_view);
        floatingActionButton = findViewById(R.id.addLink);
        //edit = findViewById(R.id.edit);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(RecyclerView.this);
                dialog.setContentView(R.layout.snack_bar);
                EditText urlIp = dialog.findViewById(R.id.Url);
                EditText nameIp = dialog.findViewById(R.id.Name);
                Button saveBtn = dialog.findViewById((R.id.Save));
                System.out.println("inside floating button");
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        url = urlIp.getText().toString();
                        name = nameIp.getText().toString();

                        String message = "";

                        if (Patterns.WEB_URL.matcher(url).matches()) {
                            message = "New link successfully added, swipe right to delete";
                            //a = new URL(url, name);
                            linkStore.add(new URL(url, name));
                            System.out.println("printing link store in recycle" + linkStore);
                        } else {
                            message = "Invalid url format";
                        }

                        newLayout = findViewById(R.id.layout);
                        Snackbar snack = Snackbar.make(newLayout, message, Snackbar.LENGTH_LONG).setAction("Action", null);
                        View snackView = snack.getView();
                        snack.show();
                        dialog.dismiss();
                    }
                });


//                edit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        url = urlIp.getText().toString();
////                        name = nameIp.getText().toString();
////                        a.setName(name);
////                        a.setUrl(url);
//                        System.out.println("edited");
//                        //linkStore.add(new URL(url, name));
//                    }
//                });

                dialog.show();
            }
        });


                linkRecyclerView.setHasFixedSize(true);
                linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                linkAdapter = (new ItemAdapter(linkStore, RecyclerView.this, this));
                new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(linkRecyclerView);
                linkRecyclerView.setAdapter(linkAdapter);


        }
    @Override
    public void onItemClick(int position) {
        setContentView(R.layout.activity_recycler_view);
        if (!url.contains("www.") && !url.startsWith("www.")) {
            url = "www." + url;
        }
        if (!url.startsWith("http") && !url.startsWith("https")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){

        @Override
        public boolean onMove(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, @NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, @NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int direction) {
            linkStore.remove(viewHolder.getAdapterPosition());
            linkAdapter.notifyDataSetChanged();
        }
    };

}