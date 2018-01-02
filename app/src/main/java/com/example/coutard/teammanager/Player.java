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



    @Override
    public String toString() {
        return "Player{" +
                " PlayerName='" + playerName + '\'' +
                ", PlayerPosition='" + playerPosition + '\'' +
                ", PlayerMail=" + playerMail +
                ", PlayerPhone=" + playerPhone +
                '}';
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.playerName);
        dest.writeString(this.playerPosition);
        dest.writeString(this.playerMail);
        dest.writeString(this.playerPhone);
    }

    protected Player(Parcel in) {
        this.playerName = in.readString();
        this.playerPosition = in.readString();
        this.playerMail = in.readString();
        this.playerPhone = in.readString();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}

