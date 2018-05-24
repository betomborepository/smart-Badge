package com.smart.badge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import Helpers.RecyclerItemClickListener;
import adapters.Profile;
import adapters.Profile_VAdapter;

import adapters.entity.Eleve;
import fragments.MainTabsFragments;

public class MainActivity extends AppCompatActivity {

    private Profile_VAdapter profile_vAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    /*This one is for eusing the option: swipe down to refresh the list*/
    private SwipeRefreshLayout swipeRefreshLayout;


    //for animating a view
    private Animation fadein, fadeout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Smart Badge");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        MainTabsFragments fragment = new MainTabsFragments();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }


    public void pointer(View v)
    {
        startActivity(new Intent(MainActivity.this, NFCPointer.class));
    }


    public void identifier (View v)
    {
        startActivity(new Intent(MainActivity.this, NFCIdentification.class));
    }

    public void goDetailEleve(View v)
    {
        Eleve el = (Eleve) v.findViewById(R.id.detail).getTag();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("eleve", el);
        startActivity(intent);
    }
}
