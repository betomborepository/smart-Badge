package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragments.Eleve;
import fragments.Home;
import fragments.ListBase;
import fragments.Pointage;

/**
 * Created by hp on 21/05/2018.
 */

public class MainPager_VAdapter  extends FragmentStatePagerAdapter
{
    private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when PermsPagerAdapter is created
    private int NumbOfTabs; // Store the number of tabs, this will also be passed when the PermsPagerAdapter is created


    public MainPager_VAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb)
    {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) { // if the position is 0 we are returning the First tab
            return new Home();
        } else if (position == 1) {
            return new Eleve();
        } else if (position == 2) {
            return new Pointage();
        } else {
            return new Pointage();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
