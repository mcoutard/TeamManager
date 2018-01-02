package com.example.coutard.teammanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COUTARD on 27/12/2017.
 */

public class Team implements Parcelable {


    private String team_name;
    private String sport_name;
    private String team_leader;
    private String training_day;
    private String training_hour;
    private List<Player> players;

    public Team(){
        players = new ArrayList<Player>() ;
        addPlayer(new Player(team_leader));
    }


    protected Team(String team_name, String sport_name, String team_leader, String training_day, String training_hour) {

        this.team_name = team_name;
        this.sport_name = sport_name;
        this.team_leader = team_leader;
        this.training_day = training_day;
        this.training_hour = training_hour;
        this.players = new ArrayList<Player>();
        addPlayer(new Player(team_leader));
    }

    public List<Player> getTeam(){
        if(players == null) players = new ArrayList<>();
        return players;
    }

    public void addPlayer(Player player){
        getTeam().add(player);
    }


    public String getTeamName() {
        return team_name;
    }

    public String getSportName() { return sport_name; }

    public String getTeamLeader() {
        return team_leader;
    }

    public String getTrainingDay() {
        return training_day;
    }

    public String getTrainingHour() {return  training_hour; }

    @Override
    public String toString() {
        return "Team{" +
                "team_name='" + team_name + '\'' +
                ", sport_name='" + sport_name + '\'' +
                ", team_leader ='" + team_leader + '\'' +
                ", training_day=" + training_day +
                ", training_hour=" + training_hour +
                ", players=" + players +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.team_name);
        dest.writeString(this.sport_name);
        dest.writeString(this.team_leader);
        dest.writeString(this.training_day);
        dest.writeString(this.training_hour);
        dest.writeTypedList(this.players);
    }

    protected Team(Parcel in) {
        this.team_name = in.readString();
        this.sport_name = in.readString();
        this.team_leader = in.readString();
        this.training_day = in.readString();
        this.training_hour = in.readString();
        this.players = in.createTypedArrayList(Player.CREATOR);
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}

