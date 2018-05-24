package service;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

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

        if (tag == null)
            return list;


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
                byte[] message = ndefRecord.getPayload();
                String textEncoding = ((message[0] & 128) == 0) ? "UTF-8" : "UTF-16";
                int languageCodeLength = message[0] & 0063;

                try {

                    String textEncoded = new String(message, languageCodeLength + 1, message.length - languageCodeLength - 1, textEncoding);
                    Log.e("read_text--------", textEncoded);
                    list.add(textEncoded);
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


    /**
     * Create a NdefRecord from a text
     * @param text
     * @return
     */
    public NdefRecord createRecord(String text){
        try{
            String lang       = "en";
            byte[] textBytes  = text.getBytes();
            byte[] langBytes  = lang.getBytes("US-ASCII");
            int    langLength = langBytes.length;
            int    textLength = textBytes.length;
            byte[] payload    = new byte[1 + langLength + textLength];
            // set status byte (see NDEF spec for actual bits)
            payload[0] = (byte) langLength;
            // copy langbytes and textbytes into payload
            System.arraycopy(langBytes, 0, payload, 1,              langLength);
            System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);
            NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,  NdefRecord.RTD_TEXT,  new byte[0], payload);
            return recordNFC;

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Create an NdefMessage form a list of NdefRecord
     * @param records
     * @return
     */

    public NdefMessage createNdefMessage(NdefRecord[] records)
    {
        NdefMessage msg = new NdefMessage(records);
        return msg;
    }

    /**
     * Write the Message to the TAG NFC
     * @param message
     * @param tag
     * @return
     */
    public static boolean writeTag(final NdefMessage message, final Tag tag)
    {
        try
        {
            int size = message.toByteArray().length;
            Ndef ndef = Ndef.get(tag);
            if (ndef == null)
            {
                //Tags qui nécessite un formatage ?
                NdefFormatable format = NdefFormatable.get(tag);
                if (format != null)
                {
                    try
                    {
                        format.connect();
                        //Formatage et écriture du message:
                        format.format(message);
                        //exemple de verrouillage du tag en écriture :
                        //formatable.formatReadOnly(message);
                        format.close() ;
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    //format == null
                    return false;
                }
            } else {
                //ndef!=null
                ndef.connect();
                if (!ndef.isWritable())
                {
                    return false;
                }
                if (ndef.getMaxSize() < size)
                {
                    return false;
                }
                ndef.writeNdefMessage(message);
                ndef.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public  boolean saveDataToTag(NdefRecord [] records, Intent intent){

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null)
            return false;

        NdefMessage ndefMessage = createNdefMessage(records);
        writeTag(ndefMessage,tag);

        return true;
    }


}
