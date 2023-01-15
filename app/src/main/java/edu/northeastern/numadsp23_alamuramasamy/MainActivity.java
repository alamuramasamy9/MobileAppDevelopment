package edu.northeastern.numadsp23_alamuramasamy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonAbtMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAbtMe = findViewById(R.id.buttonAbtMe);
        buttonAbtMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Name: Alamu Ramasamy, \nEmail: ramasamy.a@northeaastern.edu", Toast.LENGTH_LONG).show();
            }
        });
    }
}