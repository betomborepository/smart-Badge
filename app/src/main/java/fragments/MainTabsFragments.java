package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.smart.badge.R;

import adapters.MainPager_VAdapter;
import util.TabLayout;


/**
 * Created by andrew on 11/17/16.
 */

public class MainTabsFragments extends Fragment {

    ViewPager pager;
    public MainPager_VAdapter adapter;
    TabLayout tabs;
    CharSequence Titles[] = {"Home", "Elève", "Pointage"};
    int Numboftabs = 3;

    public MainTabsFragments() {
        //required empty constructor
    }

    @SuppressWarnings({"ConstantConditions", "deprecation"})
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new MainPager_VAdapter(getChildFragmentManager(), Titles, Numboftabs);

        pager = getView().findViewById(R.id.mainPager);
        pager.setAdapter(adapter);

        tabs = getView().findViewById(R.id.mainTabs);
        tabs.setBackgroundColor(Color.parseColor("#1976D2"));
        tabs.setDistributeEvenly(false);
        tabs.setCustomTabColorizer(new TabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.parseColor("#1976D2");
            }
        });
        tabs.setViewPager(pager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragments_main_tab, container, false);
    }
}
