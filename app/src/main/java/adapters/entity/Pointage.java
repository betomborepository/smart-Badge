package adapters.entity;

import java.util.Date;

/**
 * Created by hp on 22/05/2018.
 */

public class Pointage {
    public String ID;
    public String poste;
    public String date;
    public String nomEleve;
    public  String IDEleve;


    public Pointage(String id, String poste, String nomEleve, String IDEleve )
    {
        this.ID = id;
        this.poste = poste;
        this.setDate(new Date());
        this.nomEleve =nomEleve;
        this.IDEleve = IDEleve;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }



    public void setDate(String dateEntree) {
        this.date = dateEntree;
    }
    public void setDate(Date dateEntree) {
        this.date = dateEntree.toString();
    }

    public String getDate() {
        return date;
    }


}
