package service;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;


import adapters.entity.Eleve;
import adapters.entity.Pointage;

/**
 * Created by hp on 22/05/2018.
 */

public  class DataCore
{
    public static List<Eleve> GetListEleve()
    {
        List<Eleve>  listEleve = new ArrayList<Eleve>() ;
        Eleve mariot = new Eleve("BETOMBO", "Mariot", "Une personne intelligent", "077");
        Eleve matiasy = new Eleve("RASOLOFA", "Matiasy", "Une personne intelligent", "077");

        listEleve.add(mariot);
        listEleve.add(matiasy);


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




}
