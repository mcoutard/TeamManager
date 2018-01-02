package com.example.coutard.teammanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COUTARD on 27/12/2017.
 */

public class Teams {

    private List<Team> teams;

    public List<Team> getTeams(){
        if(teams == null) teams = new ArrayList<>();
        return teams;
    }

    public void addTeam (Team team) {
        getTeams().add(team);
        System.out.println("+1");
    }

    public void populate() {
        addTeam(new Team("rugby ensisa","rugby","toto","jeudi","13h"));
        addTeam(new Team("volley ensisa","rugbvolleyy","titi","mardi","19h"));

    }

}
