package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import adapters.Eleve_VAdapter;
import adapters.Profile;
import adapters.Profile_VAdapter;
import service.DataCore;


/**
 * Created by hp on 21/05/2018.
 */

public class Eleve extends ListBase {

    Eleve_VAdapter eleve_vAdapter;
    @Override
    protected void refresh_list() {

        //generating 16 profiles data ( with 5 actives)


        // putting the profile data to the adapter
        Activity a = this.getActivity();

        new DataCore().GetListEleve(a,recyclerView);
    }
}
