package service;

import android.graphics.Point;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import adapters.entity.Eleve;
import adapters.entity.Pointage;

/**
 * Created by hp on 22/05/2018.
 */

public  class DataCore
{
    private DatabaseReference mDatabase;
// ...

    public  List<Eleve> GetListEleve()
    {
        final List<Eleve>  listEleve = new ArrayList<Eleve>() ;
        mDatabase = FirebaseDatabase.getInstance().getReference("eleves").child("listeleve");
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Log.d("RealTimeDatabase", "Value is 1: " +  dataSnapshot.getValue());

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Eleve e = postSnapshot.getValue(Eleve.class);

                    listEleve.add(e);

                }

                //  ProductAdapter productAdapter = new ProductAdapter(activity, productList);
                //recyclerView.setAdapter(productAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("RealTimeDatabase", "Failed to read value.", databaseError.toException());
            }
        });
        return  listEleve;
    }


    public static List<Pointage> GetListPointage()
    {
        List<Pointage>  listPointPage = new ArrayList<Pointage>() ;
        Pointage p = new Pointage("6532", "porte4", "BETOMBO", "ID007");
        Pointage p1 = new Pointage("6532", "porte5", "Matiasy", "00715");

        listPointPage.add(p);
        listPointPage.add(p1);
        return  listPointPage;
    }


    void updatePointageEleve(Eleve el, Pointage p)
    {
        // chercher ajouter le pontage dans l'élève
        // ajouter un pointage dans le pointge genenal
    }


    public static boolean CanLog(String username, String password) {
        return  true;
    }


    public static Eleve GetEleveByImmatricule(String id)
    {
        return new Eleve("BETOMBO", "Mariot", "On a fini le projet", "15612541");
    }
}
