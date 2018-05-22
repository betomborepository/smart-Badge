package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.smart.badge.R;

import adapters.Pointage_VAdapter;
import service.DataCore;

/**
 * Created by hp on 21/05/2018.
 */

public class Pointage extends ListBase {
    Pointage_VAdapter pointage_vAdapter;
    @Override
    protected void refresh_list() {
        //generating 16 profiles data ( with 5 actives)


        // putting the profile data to the adapter
        pointage_vAdapter = new Pointage_VAdapter(DataCore.GetListPointage(), this.getActivity());

        //attaching data from the adapter to the real recyclerview
        recyclerView.setAdapter(pointage_vAdapter);
    }
}
