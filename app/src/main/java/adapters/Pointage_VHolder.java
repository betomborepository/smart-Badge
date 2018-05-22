package adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smart.badge.R;

/**
 * Created by hp on 22/05/2018.
 */

public class Pointage_VHolder extends RecyclerView.ViewHolder  {
    TextView eleve_name, pointage_heure, pointage_poste;


    public Pointage_VHolder(View itemView) {
        super(itemView);

        eleve_name = itemView.findViewById(R.id.item_title);
        pointage_heure = itemView.findViewById(R.id.item_detail);

    }
}
