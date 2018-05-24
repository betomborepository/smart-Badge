package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.smart.badge.DetailActivity;
import com.smart.badge.MainActivity;
import com.smart.badge.R;

import java.util.ArrayList;
import java.util.List;

import Helpers.RecyclerItemClickListener;
import adapters.Profile;
import adapters.Profile_VAdapter;

/**
 * Created by hp on 22/05/2018.
 */

public class ListBase extends Fragment {

    private Profile_VAdapter profile_vAdapter;
    protected RecyclerView recyclerView;
   // private FloatingActionButton fab;
    /*This one is for eusing the option: swipe down to refresh the list*/
    private SwipeRefreshLayout swipeRefreshLayout;


    //for animating a view
    private Animation fadein, fadeout;

    /**
     * Initializing views from the xml layout file
     * */



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }


    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    private void initialize() {


        recyclerView = this.getView().findViewById(R.id.recycler);
        //fab = this.getView().findViewById(R.i);
        if (recyclerView == null)
            return;
        //intializing animations that we will use later on showing and hiding the fab when the user scrolls up/down the list
        fadein = (AnimationUtils.loadAnimation(this.getActivity(), R.anim.fade_in));
        fadeout = (AnimationUtils.loadAnimation(this.getActivity(), R.anim.fade_out));


        refresh_list();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //*For using all default animations provided by the RecyclerView object
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        recyclerView.setItemAnimator(itemAnimator);

        //Adding listener and action to each recyclerview card (recyclerview item)
        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                generateDetail(v, position);
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        }));*/

        //the bottom icon, to add a new profile when it's clicked
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profile_vAdapter.addprofile();
                //swipe to the bottom to the new added profile
                recyclerView.smoothScrollToPosition(profile_vAdapter.getItemCount());
            }
        });*/


        //swipeto refresh
        swipeRefreshLayout = this.getView().findViewById(R.id.swiper);
        //swipe to refresh, when it's called
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //refreshing the list
                refresh_list();

                //stopping refresh after 2sec, to simulate that the refresh has been done
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //stopping
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        //play some color animations when the swiper is refreshing
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );



        /**
         * if you want to refresh the view at the first launch*/
        /*swipeRefreshLayout.setRefreshing(true);*/
    }


    /**
     * Metohde à réimplémenter
     */
    protected void refresh_list() {

        final List<Profile> profiles = new ArrayList<>();

        //generating 16 profiles data ( with 5 actives)
        for (int i = 0; i < 16; i++) {
            if (i < 5)
                profiles.add(new Profile(i, "Profile Name " + i, "Profile detail " + i, i + ":" + (16 - i), true));
            else if (i < 10)
                profiles.add(new Profile(i, "Profile Name " + i, "Profile detail " + i, "1" + i + ":0" + i, false));
            else
                profiles.add(new Profile(i, "Profile Name " + i, "Profile detail " + i, i + ":0" + (16 - i), false));
        }

        // putting the profile data to the adapter
        profile_vAdapter = new Profile_VAdapter(profiles, this.getActivity());

        //attaching data from the adapter to the real recyclerview
        recyclerView.setAdapter(profile_vAdapter);
    }

    protected int getLayout()
    {
        return R.layout.content_main;
    }


    protected void generateDetail(View view, int position )
    {
        Profile profile = profile_vAdapter.getProfile(position);
        if (profile != null){

            //Sending the selected profile data to the DetailActivity
            Bundle bundle = new Bundle();
            bundle.putInt("id", profile.id);
            bundle.putString("name", profile.name);
            bundle.putString("about", profile.about);
            bundle.putString("time", profile.activeTime);
            bundle.putBoolean("isActive", profile.isActive);

            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtras(bundle); //putExtras not putExtra
            startActivity(intent);


        }
    }

}
