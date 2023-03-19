package edu.northeastern.numadsp23_alamuramasamy;



import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationActivity extends AppCompatActivity implements LocationListener{

    private Button resetDistance;
    private TextView currLatitude;
    private TextView currLongitude;
    private TextView totDistance;

    private double currentDistance;
    private double latitude;
    private double longitude;

    private boolean init = true;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        resetDistance = (Button) findViewById(R.id.reset);
        currLatitude = (TextView) findViewById(R.id.latitude);
        currLongitude = (TextView) findViewById(R.id.longitude);
        totDistance =  (TextView) findViewById(R.id.totDistance);

        resetDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDistance = 0;
                totDistance.setText("Distance Covered: "+currentDistance + " km");
            }
        });

        getLocationInfo();
    }

    private void getLocationInfo(){
        if(permissionsCheck()){
            newLocation();
        }
        else{
            Log.i("TAG_123", "Requesting Permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 21);
        }

    }

    private boolean permissionsCheck(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else
            return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("MissingPermission")
    private void newLocation(){
        try{
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, LocationActivity.this);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 21) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                newLocation();
            } else {
                AlertDialog dialog = new AlertDialog.Builder(this).setMessage("This action requires permissions. " +
                                "Please allow required permissions").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Toast.makeText(LocationActivity.this,"Hey", Toast.LENGTH_SHORT).show();
                                ActivityCompat.requestPermissions(LocationActivity.this, new String[]{
                                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                                }, 21);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(LocationActivity.this, "Location permission denied.", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
//        Log.d("TAG_234", "onLocationChanged: ");
//        try {
//            setDistance(latitude,longitude,location);
//            setLocationText(location);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        float[] distanceTravelled = new float[2];
        Location.distanceBetween(this.latitude, this.longitude,
                location.getLatitude(), location.getLongitude(), distanceTravelled);
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        this.currLatitude.setText("Latitude: " + String.valueOf(latitude));
        this.currLongitude.setText("Latitude: " + String.valueOf(longitude));

        if (!init && distanceTravelled[0] != 0.0) {
            this.currentDistance += distanceTravelled[0] / 1000;
            this.totDistance.setText("Distance Covered:" + this.currentDistance + " Km");

        }
        init = false;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("TAG", "LocationProvider is available");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("TAG", "LocationProvider is out of service");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("TAG", "LocationProvider is temporarily unavailable");
                break;
        }
    }


    @Override
    public void onBackPressed(){
        new android.app.AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}

