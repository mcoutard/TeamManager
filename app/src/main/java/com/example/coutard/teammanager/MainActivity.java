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
import java.util.List;

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

        Button add = (Button) findViewById(R.id.add_team_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAdd(v);
            }
        });

        ListView teamslist = (ListView) findViewById(R.id.team_list);
        adapter = new TeamAdapter(this, teams.getTeams());
        teamslist.setAdapter(adapter);
        teamslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ici");
                Intent intent = new Intent(MainActivity.this, ViewTeamActivity.class);
                intent.putExtra("team", teams.getTeams().get((int) id));
                intent.putExtra("id", id);
                startActivityForResult(intent, TEAM_VIEW);
            }
        });
        teamslist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, final long id) {
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

    private void onClickAdd (View v) {
        Intent intent = new Intent(this, EditTeamActivity.class);
        startActivityForResult(intent, TEAM_ADD);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEAM_ADD && resultCode == RESULT_OK) {
//HM PARCELABLE maintenant si EditContact envoye un contact dans l'intent on peut faire Contact c = data.getParcelable ("contact");
            String teamName = data.getStringExtra("teamName");
            String sportName = data.getStringExtra("sportName");
            String teamLeader = data.getStringExtra("teamLeader");
            String trainingDay = data.getStringExtra("trainingDay");
            String trainingHour = data.getStringExtra("trainingHour");
            Team team = new Team(teamName, sportName, teamLeader, trainingDay, trainingHour);
            teams.addTeam(team);
            System.out.println(team.toString());
            adapter.notifyDataSetChanged();
            Log.d ("MainActivity", team.toString());
        }
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}

