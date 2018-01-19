package com.example.coutard.teammanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COUTARD on 27/12/2017.
 */

/*
Cette classe va nous permettre de gérer ma liste d'équipe, celle qui sera visible dans le MainActivity
 */

public class Teams {

    private List<Team> teams;

    //On récupère notre liste d'équipes
    public List<Team> getTeams(){
        if(teams == null) teams = new ArrayList<>();
        return teams;
    }

    //On peut ajouter des équipes dans notre liste d'équipes
    public void addTeam (Team team) {
        getTeams().add(team);
        System.out.println("+1");
    }

    //On va ajouter plusieurs listes au départ ppour avoir un avant gout de ce qui se prépare
    public void populate() {
        addTeam(new Team("RUGBY ENSISA","RUGBY","MAXIME","jeudi","13h"));
        addTeam(new Team("FOOT ENSISA","FOOTBALL","KEVIN","jeudi","14h"));
        addTeam(new Team("JUDO ENSISA","JUDO","JEAN-BAPTISTE","mercredi","18h"));
        addTeam(new Team("ESCALADE ENSISA","ESCALADE","LUCAS","jeudi","15h"));
        addTeam(new Team("DANSE ENSISA","DANSE","SIMON","mardi","18h"));
        addTeam(new Team("VOLLEY ENSISA","VOLLEY","TITI","mardi","19h"));

    }

}
