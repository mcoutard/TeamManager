package com.example.coutard.teammanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COUTARD on 28/12/2017.
 */

public class ViewTeamActivity extends AppCompatActivity {


    private static final int PLAYER_ADD = 1;


    private TextView teamName_ui;
    private TextView sportName_ui;
    private TextView teamLeader_ui;
    private TextView trainingDay_ui;
    private TextView trainingHour_ui;
    private Team team;
    private PlayerAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);

        System.out.println("qui va la");
       // team = new Team();

        Button add = (Button) findViewById(R.id.add_player_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddPlayer(v);
            }
        });

        teamName_ui = (TextView) findViewById(R.id.textViewTeamName);
        sportName_ui = (TextView) findViewById(R.id.textViewSportName);
        teamLeader_ui = (TextView) findViewById(R.id.textViewTeamLeader);
        trainingDay_ui = (TextView) findViewById(R.id.textViewTrainingDay);
        trainingHour_ui = (TextView) findViewById(R.id.textViewTrainingHour);

        ListView playersList = (ListView) findViewById(R.id.playersList);


        if (getIntent().hasExtra("team")) {
            Team team = getIntent().getParcelableExtra("team");

            teamName_ui.setText(team.getTeamName());
            sportName_ui.setText(team.getSportName());
            System.out.println("blabla" +team.toString());
            teamLeader_ui.setText(team.getTeamLeader());
            trainingDay_ui.setText(team.getTrainingDay());
            trainingHour_ui.setText(team.getTrainingHour());

            this.team = team;
            adapter = new PlayerAdapter(this, this.team.getTeam());
            playersList.setAdapter(adapter);
        }



    }



    private class PlayerAdapter extends ArrayAdapter<Player> {

        public PlayerAdapter(@NonNull Context context, @NonNull List<Player> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        private class PlayerHolder {
            public TextView playerName;
            public TextView playerPosition;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewTeamActivity.PlayerAdapter.PlayerHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.player_item, null);
                holder = new ViewTeamActivity.PlayerAdapter.PlayerHolder();
                holder.playerName = (TextView) convertView.findViewById(R.id.player_name);
                holder.playerPosition = (TextView) convertView.findViewById(R.id.player_position    );
                convertView.setTag(holder);
            } else {
                holder = (ViewTeamActivity.PlayerAdapter.PlayerHolder) convertView.getTag();
            }
            Player player = getItem(position);
            holder.playerName.setText(player.getPlayerName());
            holder.playerPosition.setText(player.getPlayerPosition());
            return convertView;
        }
    }

    public void onClickAddPlayer(View v){
        Intent intent = new Intent(this, EditPlayerActivity.class);
        startActivityForResult(intent, PLAYER_ADD);
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAYER_ADD && resultCode == RESULT_OK) {
//HM PARCELABLE maintenant si EditContact envoye un contact dans l'intent on peut faire Contact c = data.getParcelable ("contact");
            String playerName = data.getStringExtra("playerName");
            String playerPosition = data.getStringExtra("playerPosition");
            String playerMail = data.getStringExtra("playerMail");
            String playerPhone = data.getStringExtra("playerPhone");
            Player player = new Player(playerName, playerPosition, playerMail, playerPhone);
            team.addPlayer(player);
            System.out.println(team.toString());
            adapter.notifyDataSetChanged();
            Log.d ("ViewTeamActivity", team.toString());
        }
        /*
        if (requestCode == TEAM_VIEW && resultCode == RESULT_OK) {
            long id = data.getLongExtra("id", -1);
            teams.getTeams().remove((int) id);
//HM PARCELABLE idem que pour ADD
            String teamName = data.getStringExtra("teamName");
            String sportName = data.getStringExtra("sportName");
            String teamLeader = data.getStringExtra("teamLeader");
            String trainingDay = data.getStringExtra("trainingDay");
            String trainingHour = data.getStringExtra("trainingHour");
            Team team = new Team(teamName, sportName, teamLeader, trainingDay, trainingHour);
            teams.addTeam(team);
            adapter.notifyDataSetChanged();
            Log.d ("MainActivity", team.toString());
        }
*/
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onCancel (View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onValid (View v){

        String teamName = teamName_ui.getText().toString();
        String sportName = sportName_ui.getText().toString();
        String teamLeader = teamLeader_ui.getText().toString();
        String trainingDay = trainingDay_ui.getText().toString();
        String trainingHour = trainingHour_ui.getText().toString();
        List<Player> players = team.getTeam();

        Intent intent = getIntent();
        intent.putExtra("teamName", teamName);
        intent.putExtra("sportName", sportName);
        intent.putExtra("teamLeader", teamLeader);
        intent.putExtra("trainingDay", trainingDay);
        intent.putExtra("trainingHour", trainingHour);
        intent.putExtra("players", (Serializable) players);
        setResult(RESULT_OK, intent);
        finish();
    }



}
