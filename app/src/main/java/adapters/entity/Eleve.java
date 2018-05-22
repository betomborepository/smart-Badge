package adapters.entity;

/**
 * Created by hp on 22/05/2018.
 */

public class Eleve {
    public String name;
    public  String surName;
    public  String description;
    public  String immatricul;


    Eleve(String name, String surName, String description, String immatricul)
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
        return listName.substring(0, 20) + "...";
    }
}

