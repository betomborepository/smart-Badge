package com.hdev.developer.example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import adapters.Profile;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView detail_name, detail_about, detail_active_time;

    private Profile profile;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Getting data from the previous activity (MainActivity)
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            try {
                profile = new Profile(
                        bundle.getInt("id"),
                        bundle.getString("name"),
                        bundle.getString("about"),
                        bundle.getString("time"),
                        bundle.getBoolean("isActive")
                );

                initialize(profile);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "ERROR on parsing received data: \n"+e.toString(),
                        Toast.LENGTH_LONG).show();
            }

        }


    }

    private void initialize(Profile profile) {

        toolbar.setTitle(profile.name);

        detail_name = findViewById(R.id.detail_name);
        detail_about = findViewById(R.id.detail_about);
        detail_active_time = findViewById(R.id.detail_active);

        detail_name.setText(getResources().getString(R.string._profile_detail, profile.name));
        detail_about.setText(getResources().getString(R.string._profile_detail, profile.about));

        if (profile.isActive)
            detail_active_time.setText(getResources().getString(R.string._profile_detail, getResources().getString(R.string.now)));
        else
            detail_active_time.setText(getResources().getString(R.string._profile_detail, profile.activeTime));



    }

}
