package service;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hp on 23/05/2018.
 */

public class NFCCore {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static String getNFCFirstRecordValue (Intent intent)
    {
        Log.d("Entree processNfcIntent","mmmmmm-processIntent");
        //Infos sur le tag
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Ndef ndef = Ndef.get(tag);
        NdefMessage ndefMessage = ndef.getCachedNdefMessage();
        NdefRecord[] ndefrecords = ndefMessage.getRecords();
        for (NdefRecord ndefRecord : ndefrecords){
            if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT))
            {
                int i=0;
                String paylod="";
                byte[] payload = ndefRecord.getPayload();
                while (i<payload.length){
                    paylod=paylod+payload[i];
                    i++;
                }
                try {

                    Log.e("read_text--------", new String(payload, "UTF16"));

                    return new String(payload, "UTF16");
                }
                catch (Exception exc){

                }
            }
        }

        byte[] id =tag.getId();
        int i=0;
        String idstring="";
        while (i<id.length)
        {
            idstring=idstring+id[i];
            i++;
        }

        return "";

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static List<String> getNFCRecordList(Intent intent)
    {

        List<String> list= new ArrayList<>();
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Ndef ndef = Ndef.get(tag);
        NdefMessage ndefMessage = ndef.getCachedNdefMessage();
        NdefRecord[] ndefrecords = ndefMessage.getRecords();
        for (NdefRecord ndefRecord : ndefrecords){
            if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT))
            {
                int i=0;
                String paylod="";
                byte[] payload = ndefRecord.getPayload();
                while (i<payload.length){
                    paylod=paylod+payload[i];
                    i++;
                }
                try {

                    Log.e("read_text--------", new String(payload, "UTF16"));
                    list.add(new String(payload, "UTF16"));
                }
                catch (Exception exc){

                }
            }
        }

        byte[] id =tag.getId();
        int i=0;
        String idstring="";
        while (i<id.length)
        {
            idstring=idstring+id[i];
            i++;
        }

        return list;

    }


}
