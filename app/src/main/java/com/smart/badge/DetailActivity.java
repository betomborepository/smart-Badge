package com.smart.badge;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import adapters.Profile;
import adapters.entity.Eleve;

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


    private void initialize(Eleve el) {

        toolbar.setTitle(profile.name);
        AppCompatTextView detail_class = findViewById();
        AppCompatTextView detail_immatricul = findViewById()
        AppCompatTextView detail_surName = findViewById();
        AppCompatTextView detail_description = findViewById();

        detail_immatricul.setText(el.immatricul);
        detail_name.setText(el.name);
        detail_surName.setText(el.surName);
        detail_class.setText(el.classe);
    }

}
