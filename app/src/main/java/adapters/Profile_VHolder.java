package adapters;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smart.badge.R;

/**
 * This class is for initializing the views from card_profile
 * */

public class Profile_VHolder extends RecyclerView.ViewHolder {

    AppCompatImageView profile_ic, profile_active_ic;
    AppCompatTextView profile_name, profile_detail, profile_active_time;

    public Profile_VHolder(View itemView) {
        super(itemView);

        profile_ic = itemView.findViewById(R.id.profile_ic);
        profile_active_ic = itemView.findViewById(R.id.profile_active_ic);

        profile_name= itemView.findViewById(R.id.title);
        profile_detail= itemView.findViewById(R.id.detail);
        profile_active_time= itemView.findViewById(R.id.active_time);
    }
}
