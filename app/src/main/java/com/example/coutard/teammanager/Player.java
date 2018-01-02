package com.example.coutard.teammanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by COUTARD on 31/12/2017.
 */

public class Player implements Parcelable {

    private String playerName;
    private String playerPosition;
    private String playerMail;
    private String playerPhone;

    public Player(String _playerName){
        this.playerName=_playerName;
        this.playerPosition="";
        this.playerMail = "";
        this.playerPhone = "";
    }

    public Player(String _playerName, String _playerPosition, String _playerMail, String _playerPhone){
        this.playerName = _playerName;
        this.playerPosition = _playerPosition;
        this.playerMail = _playerMail;
        this.playerPhone = _playerPhone;
    }

    protected Player(Parcel in) {
        playerName = in.readString();
        playerPosition = in.readString();
        playerMail = in.readString();
        playerPhone = in.readString();
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };


    @Override
    public String toString() {
        return "Player{" +
                " PlayerName='" + playerName + '\'' +
                ", PlayerPosition='" + playerPosition + '\'' +
                ", PlayerMail=" + playerMail +
                ", PlayerPhone=" + playerPhone +
                '}';
    }


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeString(playerPosition);
        dest.writeString(playerMail);
        dest.writeString(playerPhone);
    }

    public String getPlayerName() {
        if(playerName != null)
            return playerName;
        else
            return "";
    }

    public String getPlayerPosition() {
        if(playerPosition != null)
            return playerPosition;
        else
            return "";
    }

    public String getPlayerMail() {
        if(playerMail != null)
            return playerMail;
        else
            return "";
    }

    public String getPlayerPhone() {
        if(playerPhone != null)
            return playerPhone;
        else
            return "";
    }
}

