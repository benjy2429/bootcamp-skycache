package com.sky.bootcamp.geocache.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sky.bootcamp.geocache.R;
import com.sky.bootcamp.geocache.models.User;

/**
 * Created by bca23 on 25/07/15.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Declaring Your View and Variables
    Toolbar toolbar;
    MapFragment mapFragment;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(MapActivity.this);
                scanIntegrator.initiateScan();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();

            if (scanContent != null) {

                if (scanContent.startsWith("skycash")) {
                    Intent rewardIntent = new Intent(this, RewardActivity.class);
                    rewardIntent.putExtra("currentUser", currentUser);
                    startActivity(rewardIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid barcode", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Disable the back button
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);

        addGeocache(53.795459, -1.552803, map);
        addGeocache(53.796351, -1.545103, map);
        addGeocache(53.800356, -1.549609, map);
        addGeocache(53.795566, -1.563084, map);
        addGeocache(53.790242, -1.552398, map);

    }

    private void addGeocache(double lat, double lon, GoogleMap map) {
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(lat, lon))
                .fillColor(Color.argb(128, 10, 133, 200))
                .strokeWidth(0)
                .radius(50);

        map.addCircle(circleOptions);
    }
}