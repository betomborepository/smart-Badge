package adapters.entity;

import android.graphics.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 22/05/2018.
 */

public class Eleve implements Serializable{
    public String name;
    public  String surName;
    public  String classe;
    public  String description;
    public  String immatricul;
    List<Pointage> listPointage;

    public Eleve(){

    }
    public Eleve(String name, String surName, String description, String immatricul)
    {
        this.name = name;
        this.surName = surName;
        this.description = description;
        this.immatricul = immatricul;
    }


    public String shortDescription()
    {
        return description.substring(0, 20) + "...";
    }

    public String getListName()
    {
        String listName = name + " " + surName + " " + immatricul;
        int tailleLimit = 20;
        if(listName.length() >= tailleLimit)
        return listName.substring(0, tailleLimit) + "...";

        return  listName;
    }

    public void generatePointage()
    {
        listPointage = new ArrayList<Pointage>();


    }
}

