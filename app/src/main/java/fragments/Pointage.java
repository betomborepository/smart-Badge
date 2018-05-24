package fragments;


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

        new DataCore().GetListPointage(this.getActivity(),recyclerView);

    }
}
