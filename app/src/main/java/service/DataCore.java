package service;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;


import adapters.entity.Eleve;
import fragments.Pointage;

/**
 * Created by hp on 22/05/2018.
 */

public  class DataCore
{
    public static List<Eleve> GetListEleve()
    {
        List<Eleve>  listEleve = new ArrayList<Eleve>() ;

        return  listEleve;
    }


    public static List<Pointage> GetListPointage()
    {
        List<Pointage>  listPointPage = new ArrayList<Pointage>() ;

        return  listPointPage;
    }


    void updatePointageEleve(Eleve el, Pointage p)
    {
        // chercher ajouter le pontage dans l'élève
        // ajouter un pointage dans le pointge genenal
    }




}
