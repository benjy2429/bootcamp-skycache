package com.sky.bootcamp.geocache.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sky.bootcamp.geocache.R;
import com.sky.bootcamp.geocache.database.DatabaseAccessLayer;
import com.sky.bootcamp.geocache.models.User;

import java.sql.SQLException;
import java.util.Random;

public class RewardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private User currentUser;
    private UpdatePointsTask mUpdateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        currentUser = (User) getIntent().getSerializableExtra("currentUser");

        TextView pointsView = (TextView) findViewById(R.id.points);
        int points = generateRandomPoints() * 100;
        pointsView.setText(points + "");

        mUpdateTask = new UpdatePointsTask(currentUser, points);
        mUpdateTask.execute((Void) null);

        Button mEmailSignInButton = (Button) findViewById(R.id.back_to_map_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    private int generateRandomPoints() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt((10 - 1) + 1) + 1;
    }

    public class UpdatePointsTask extends AsyncTask<Void, Void, Void> {

        private final User mUser;
        private final int mPoints;

        UpdatePointsTask(User user, int points) {
            mUser = user;
            mPoints = points;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                DatabaseAccessLayer.updateUserPoints(mUser, mPoints);
            } catch (SQLException | NullPointerException e) {
            }
            return null;
        }
    }
}
