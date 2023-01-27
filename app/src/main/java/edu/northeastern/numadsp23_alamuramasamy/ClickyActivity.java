package edu.northeastern.numadsp23_alamuramasamy;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyActivity extends AppCompatActivity {

    private Button A;
    private Button B;
    private Button C;
    private Button D;
    private Button E;
    private Button F;
    private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        A = findViewById(R.id.A);
        B = findViewById(R.id.B);
        C = findViewById(R.id.C);
        D = findViewById(R.id.D);
        E = findViewById(R.id.E);
        F = findViewById(R.id.F);
        output = findViewById(R.id.output);

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Pressed : A");
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Pressed : B");
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Pressed : C");
            }
        });

        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Pressed : D");
            }
        });

        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Pressed : E");
            }
        });

        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Pressed : F");
            }
        });

    }
}
