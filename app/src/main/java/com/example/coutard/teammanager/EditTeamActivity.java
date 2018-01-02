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

public class EditTeamActivity extends AppCompatActivity {

    private EditText teamName_ui;
    private EditText sportName_ui;
    private EditText teamLeader_ui;
    private Spinner trainingDay_ui;
    private EditText trainingHour_ui;


    public EditTeamActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);

        teamName_ui = (EditText) findViewById(R.id.team_name);
        sportName_ui = (EditText) findViewById(R.id.sport_name);
        teamLeader_ui = (EditText) findViewById(R.id.team_leader);
        trainingDay_ui = (Spinner) findViewById(R.id.training_day);
        trainingHour_ui = (EditText) findViewById(R.id.training_hour);

    }





    public void onValid (View v) {
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
        intent.putExtra("trainingHourt", trainingHour);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onCancel (View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
