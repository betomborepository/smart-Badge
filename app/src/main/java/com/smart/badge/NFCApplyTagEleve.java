package com.smart.badge;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import adapters.entity.Eleve;
import service.DataCore;
import service.NFCCore;


public class NFCApplyTagEleve extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private DatabaseReference mDatabase;
    Eleve currentEleve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0,new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        currentEleve =  (Eleve) getIntent().getSerializableExtra("eleve");
        Toast.makeText(getApplicationContext(), "Approcher voter badge pour assigner l'immatricul de l'élève au tag",
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

            assignerTag(intent);
        }
    }

    public void assignerTag(Intent intent)
    {
        NdefRecord[] records= { new NFCCore().createRecord(currentEleve.immatricul)};
       if( new NFCCore().saveDataToTag(records,intent))
        {
            Toast.makeText(getApplicationContext(), "Ce tag a été assigné avec succés à " + currentEleve.surName + " avec comme immatriucle " + currentEleve.immatricul,
                    Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(NFCApplyTagEleve.this, MainActivity.class);
            intent.putExtra("eleve",currentEleve);
            startActivity(intent2);
        }else
        {
            error();
        }
    }

    public void error()
    {
        Toast.makeText(getApplicationContext(), "Le tag n'est pas assez proche!",
                Toast.LENGTH_SHORT).show();
    }

}
