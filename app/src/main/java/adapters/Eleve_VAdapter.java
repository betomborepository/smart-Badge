package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import java.util.List;

import adapters.entity.Eleve;


/**
 * Created by hp on 22/05/2018.
 */

public class Eleve_VAdapter extends RecyclerView.Adapter<Eleve_VHolder>
{
    private List<Eleve> ListEleves;
    private  Context context;

    public  Eleve_VAdapter(List<Eleve> eleves, Context context)
    {
        this.ListEleves =eleves;
        this.context = context;
    }


    @NonNull
    @Override
    public Eleve_VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_eleve, parent, false);
        return new Eleve_VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Eleve_VHolder holder, int position) {
        Eleve el = getEleve(position);

        holder.itemView.setTag(el);
        holder.eleve_name.setText(el.getListName());
        holder.eleve_name.setTag(el);
        holder.eleve_detail.setText(el.shortDescription());
        holder.eleve_detail.setTag(el);
        holder.eleve_name.setTag(el);
        // populate the view holder
    }

    @Override
    public int getItemCount() {
        return ListEleves.size();
    }


    private  Eleve getEleve(int position)
    {
        return  ListEleves.get(position);
    }

}
