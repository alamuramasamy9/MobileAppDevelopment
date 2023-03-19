package edu.northeastern.numadsp23_alamuramasamy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonAbtMe;
    Button buttonClicky;
    Button linkCollector;
    Button buttonPrime;

    Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAbtMe = findViewById(R.id.buttonAbtMe);

        buttonAbtMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Name: Alamu Ramasamy, \nEmail: ramasamy.a@northeaastern.edu", Toast.LENGTH_LONG).show();
                openAboutMe();
            }

        });

        buttonClicky = findViewById(R.id.buttonClicky);

        buttonClicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  openClicky();
            }
        });

        linkCollector = findViewById(R.id.LinkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openRecyclerView();}
        });

        buttonPrime = findViewById(R.id.primeNo);
        buttonPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrimeView();
            }
        });

        location = findViewById(R.id.locationB);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocation();
            }

        });
    }

    public void openClicky(){
        Intent intent = new Intent(this, ClickyActivity.class);
        startActivity(intent);
    }

    private void openRecyclerView() {
        Intent recycleIntent = new Intent(this, RecyclerView.class);
        startActivity(recycleIntent);
    }

    public void openAboutMe(){
        Intent intent = new Intent(this, AboutMe.class);
        startActivity(intent);
    }

    private void openPrimeView() {
        Intent primeIntent = new Intent(this, PrimeNumberView.class);
        startActivity(primeIntent);
    }

    private void openLocation() {
        Intent location = new Intent(this, LocationActivity.class);
        startActivity(location);
    }

}