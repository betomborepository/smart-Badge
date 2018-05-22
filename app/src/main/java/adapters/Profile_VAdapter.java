package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.smart.badge.R;

import java.util.Collections;
import java.util.List;

public class Profile_VAdapter extends RecyclerView.Adapter<Profile_VHolder> {

    AppCompatImageView profile_ic, profile_active_ic;
    AppCompatTextView profile_name, profile_detail, profile_active_time;

    private List<Profile> list = Collections.emptyList();
    private Context context;
    private static final String TAG = Profile_VAdapter.class.getSimpleName();

    public Profile_VAdapter(List<Profile> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     *
     * Inflating view for each profile*/
    @NonNull
    @Override
    public Profile_VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile, parent, false);
        return new Profile_VHolder(view);
    }


    /**
     * Setting up each card_profile view with specific profile data
     * */
    @Override
    public void onBindViewHolder(@NonNull Profile_VHolder holder, int position) {

        Profile profile = getProfile(position);

        holder.profile_name.setText(profile.name);
        holder.profile_detail.setText(profile.about);

        if (profile.isActive){
            holder.profile_active_ic.setVisibility(View.VISIBLE);
            holder.profile_active_time.setVisibility(View.GONE);
        }else{
            holder.profile_active_ic.setVisibility(View.GONE);
            holder.profile_active_time.setVisibility(View.VISIBLE);
            holder.profile_active_time.setText(profile.activeTime);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Profile getProfile(int position) {
        return list.get(position);
    }

    public void addprofile() {
        int i = getItemCount();

        Profile profile = new Profile(i, "Profile Name "+i, "Profile detail "+i,
                i-1+":"+i, false);

        list.add(profile);
        int index = list.indexOf(profile);

        notifyItemInserted(index);
    }
}
