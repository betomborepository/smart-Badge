package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart.badge.R;

import java.util.List;

import adapters.entity.Pointage;


/**
 * Created by hp on 22/05/2018.
 */

public class Pointage_VAdapter  extends   RecyclerView.Adapter<Pointage_VHolder>
{

    private List<Pointage> listPointages;
    private Context context;

    public  Pointage_VAdapter(List<Pointage> eleves, Context context)
    {
        this.listPointages =eleves;
        this.context = context;
    }


    @NonNull
    @Override
    public Pointage_VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile_pointage, parent, false);
        return new Pointage_VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pointage_VHolder holder, int position) {
        Pointage p = getPointage(position);

        holder.eleve_name.setText(p.nomEleve);
        holder.pointage_heure.setText(p.getDate());
        //holder.pointage_poste.setText(p.getPoste());
    }

    @Override
    public int getItemCount() {
        return listPointages.size();
    }

    private  Pointage getPointage(int position)
    {
        return  listPointages.get(position);
    }
}
