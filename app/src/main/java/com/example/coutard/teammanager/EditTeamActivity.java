package com.example.coutard.teammanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
/**
 * Created by COUTARD on 27/12/2017.
 */

/*
Cette classe sert à créer le nouvelles équipes.

 */

public class EditTeamActivity extends AppCompatActivity {

    private EditText teamName_ui;
    private EditText sportName_ui;
    private EditText teamLeader_ui;
    private Spinner trainingDay_ui;
    private EditText trainingHour_ui;


    // Le onCreate correspond à ce qui va se lancer au chargement de la vue
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);//On précise à quelle vue correspond la classe

        teamName_ui = findViewById(R.id.team_name);
        sportName_ui = findViewById(R.id.sport_name);
        teamLeader_ui = findViewById(R.id.team_leader);
        trainingDay_ui = findViewById(R.id.training_day);
        trainingHour_ui = findViewById(R.id.training_hour);

    }


//Et ensuite lorsque l'on valide , on envoie les informations remplies dans la vue au dessus(dans
//notre cas , c'est la MainActivity

    public void onValid (View v) {

        if (!teamName_ui.getText().toString().matches("")
                &&!sportName_ui.getText().toString().matches("")
                && !teamLeader_ui.getText().toString().matches("")  ){
            String teamName = teamName_ui.getText().toString();
            String sportName = sportName_ui.getText().toString();
            String teamLeader = teamLeader_ui.getText().toString();
            String trainingDay = trainingDay_ui.getSelectedItem().toString();
            String trainingHour = trainingHour_ui.getText().toString();

            Intent intent = getIntent();
            intent.putExtra("teamName", teamName);
            intent.putExtra("sportName", sportName);
            intent.putExtra("teamLeader", teamLeader);
            intent.putExtra("trainingDay", trainingDay);
            intent.putExtra("trainingHour", trainingHour);
            setResult(RESULT_OK, intent);
            finish();
        }
        if(teamName_ui.getText().toString().matches("")){
            teamName_ui.setError(getResources().getString(R.string.EnterNameTeam));
        }
        if(sportName_ui.getText().toString().matches("")){
            sportName_ui.setError(getResources().getString(R.string.EnterSportName));
        }
        if(teamLeader_ui.getText().toString().matches("")){
            teamLeader_ui.setError(getResources().getString(R.string.EnterLeader));
        }


}

    public void onCancel (View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
