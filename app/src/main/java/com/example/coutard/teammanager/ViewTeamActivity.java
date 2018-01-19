package com.example.coutard.teammanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by COUTARD on 28/12/2017.
 */

/*
Cette classe est finalement la plus importante
C'est à partir d'ici ou on peut visionner les informations de notre équipe,
on peut voir la liste des joueurs (toute grâce à la scrollbar
 */

public class ViewTeamActivity extends AppCompatActivity {


    private static final int PLAYER_ADD = 1;
    private static final int CONTACT_TEAM_EMAIL = 2;
    private static final int PLAYER_EDIT= 3;



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

        FloatingActionButton add_player_button =  findViewById(R.id.add_player_button);
        add_player_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddPlayer(v);
            }
        });


        Button contact_team_email = findViewById(R.id.contact_team_button_mail);
        contact_team_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickContactTeamEmail(v);
            }
        });



        teamName_ui = findViewById(R.id.textViewTeamName);
        sportName_ui = findViewById(R.id.textViewSportName);
        teamLeader_ui = findViewById(R.id.textViewTeamLeader);
        trainingDay_ui = findViewById(R.id.textViewTrainingDay);
        trainingHour_ui = findViewById(R.id.textViewTrainingHour);

        final ListView playersList = findViewById(R.id.playersList);

        playersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ici");
                Intent intent = new Intent(ViewTeamActivity.this, EditPlayerActivity.class);
                intent.putExtra("player", team.getTeam().get((int) id));
                intent.putExtra("id", id);
                startActivityForResult(intent, PLAYER_EDIT);
            }
        });

        playersList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, final long id) {
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        team.getTeam().remove((int) id);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
                return true;
            }
        });

        if (getIntent().hasExtra("team")) {
            Team team = getIntent().getParcelableExtra("team");

            teamName_ui.setText(team.getTeamName());
            sportName_ui.setText(team.getSportName());
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
                holder.playerName = convertView.findViewById(R.id.player_name);
                holder.playerPosition = convertView.findViewById(R.id.player_position );
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



    public void onClickContactTeamEmail(View v){
        Intent intent = new Intent(this, EditEmailActivity.class);
        intent.putExtra("team",team);
        startActivityForResult(intent, CONTACT_TEAM_EMAIL);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAYER_ADD && resultCode == RESULT_OK) {
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

        if (requestCode == CONTACT_TEAM_EMAIL && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
            Log.d ("MainActivity", team.toString());
        }

        if (requestCode == PLAYER_EDIT && resultCode == RESULT_OK){
            long id = data.getLongExtra("id", -1);
            team.getTeam().remove((int) id);
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
