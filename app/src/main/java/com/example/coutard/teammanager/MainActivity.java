package com.example.coutard.teammanager;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
Cette vue est la vue principale , et j'ai décidé d'y afficher ma liste d'équipes
 */

public class MainActivity extends AppCompatActivity {

    private static final int TEAM_ADD = 1;
    private static final int TEAM_VIEW = 2;

    private Teams teams;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teams = new Teams();
        teams.populate ();

        Button add = findViewById(R.id.add_team_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAdd(v);
            }
        });

        ListView teamslist = findViewById(R.id.team_list);
        adapter = new TeamAdapter(this, teams.getTeams());
        teamslist.setAdapter(adapter);//Les cases de ma listes seront custom grâce à mon TeamAdapter
        teamslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Lorsque l'utilisateur clique sur une case, c'est ici que j'envoie mes informations de la case
        // vers mon ViewTeamActivity
                Intent intent = new Intent(MainActivity.this, ViewTeamActivity.class);
                intent.putExtra("team", teams.getTeams().get((int) id));
                intent.putExtra("id", id);
                startActivityForResult(intent, TEAM_VIEW);
            }
        });
        teamslist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, final long id) {
                //Ici je gère la suppression des cases.
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        teams.getTeams().remove((int) id);
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
                return true;
            }
        });

    }


    private void onClickAdd (View v) {
        Intent intent = new Intent(this, EditTeamActivity.class);
        startActivityForResult(intent, TEAM_ADD);
    }

/*
Dans mon TeamAdapter je customise mes cases
Il a simplement besoin d'un constructeur et d'un getView()
 */
    private class TeamAdapter extends ArrayAdapter<Team> {

        public TeamAdapter(@NonNull Context context, @NonNull List<Team> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }

        private class TeamHolder {
            public TextView teamName;
            public TextView sportName;
            public TextView teamLeader;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            TeamHolder holder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.team_item, null);
                holder = new TeamHolder();
                holder.teamName = (TextView) convertView.findViewById(R.id.team_name);
                holder.sportName = (TextView) convertView.findViewById(R.id.sport_name);
                holder.teamLeader = (TextView) convertView.findViewById(R.id.team_leader);
                convertView.setTag(holder);
            } else {
                holder = (TeamHolder) convertView.getTag();
            }
            Team team = getItem(position);
            holder.teamName.setText(team.getTeamName());
            holder.sportName.setText(team.getSportName());
            holder.teamLeader.setText(team.getTeamLeader());
            return convertView;
        }
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Ici on gère l'action de recevoir une nouvelle équipe
        if (requestCode == TEAM_ADD && resultCode == RESULT_OK) {
            String teamName = data.getStringExtra("teamName");
            String sportName = data.getStringExtra("sportName");
            String teamLeader = data.getStringExtra("teamLeader");
            String trainingDay = data.getStringExtra("trainingDay");
            String trainingHour = data.getStringExtra("trainingHour");
            Team team = new Team(teamName, sportName, teamLeader, trainingDay, trainingHour);
            teams.addTeam(team);
            System.out.println(team.toString());
            adapter.notifyDataSetChanged();
        }
        //Ici on gère l'action de la modification d'une liste
        if (requestCode == TEAM_VIEW && resultCode == RESULT_OK) {
            //On commence par supprimer la liste avant changement
            long id = data.getLongExtra("id", -1);
            teams.getTeams().remove((int) id);
            //Puis on ajoute la liste après changement
            String teamName = data.getStringExtra("teamName");
            String sportName = data.getStringExtra("sportName");
            String teamLeader = data.getStringExtra("teamLeader");
            String trainingDay = data.getStringExtra("trainingDay");
            String trainingHour = data.getStringExtra("trainingHour");
            List<Player> players = data.getParcelableArrayListExtra("players");
            Team team = new Team(teamName, sportName, teamLeader, trainingDay, trainingHour, players);
            teams.addTeam(team);
            adapter.notifyDataSetChanged();
        }

    }

    //Cette méthode est juste utile pour utiliser le bouton du téléphone en bas à gauche.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}

