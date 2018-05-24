package com.smart.badge;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import adapters.entity.Eleve;
import adapters.entity.Pointage;
import adapters.entity.User;
import service.DataCore;
import service.NFCCore;


public class NFCPointer extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcpointer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0,new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);


        Toast.makeText(getApplicationContext(), "Approcher voter badge pour pointer l'élève",
                Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onResume()
    {
        super.onResume();
        IntentFilter[] intentFiltersArray = new IntentFilter[]{};
        String[][] techListsArray = new String[][]{
                {android.nfc.tech.Ndef.class.getName()},
                {android.nfc.tech.NdefFormatable.class.getName()}};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray,
                techListsArray);
        Log.e("Entree NewIntent","mmmmmmmmm-onresume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
        Log.e("Entree NewIntent","mmmmmmmmmm-onpause");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.e("Entree NewIntent","mmmmmmmmmm-onNewIntent");
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(action))
        {
            //Méthode qui va traiter le tag NFC
            Log.e("Entree NewIntent","mmmmmmmmmm-onNewIntent");
            List<String> nfcVal = NFCCore.getNFCRecordList(intent);

            pointerUnEleve(nfcVal);
        }
    }

    public void pointerUnEleve(final List<String> listId)
    {
       //adapters.entity.Eleve el = DataCore.GetEleveByImmatricule(id);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("eleves");
        ValueEventListener valueEventListener = mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Eleve eleve = postSnapshot.getValue(Eleve.class);
                    if(listId.contains(eleve.immatricul) )
                    {

                        new DataCore().updatePointageEleve(eleve);
                        Toast.makeText(getApplicationContext(), "l'élève a été pointer. Verifier son heure dans la secion pointage",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NFCPointer.this, MainActivity.class);
                        startActivity(intent);
                        return;
                    }

                    errorPointage();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.wtf("une erreur dans firebase a été signalé", "une erreu dans firebase a été signalé");
            }
        });

    }

    public void errorPointage()
    {
        Toast.makeText(getApplicationContext(), "Elève non reconnu!",
                Toast.LENGTH_SHORT).show();
    }


}
