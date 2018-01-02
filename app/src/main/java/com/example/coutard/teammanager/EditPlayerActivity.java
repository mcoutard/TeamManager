package com.example.coutard.teammanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by COUTARD on 31/12/2017.
 */

public class EditPlayerActivity extends AppCompatActivity {

    private EditText playerName_ui;
    private EditText player_position_ui;
    private EditText playerMail_ui;
    private EditText playerPhone_ui;

    public EditPlayerActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        playerName_ui = (EditText) findViewById(R.id.EditPlayerName);
        player_position_ui = (EditText) findViewById(R.id.EditPlayerPosition);
        playerMail_ui = (EditText) findViewById(R.id.EditPlayerMailAdress);
        playerPhone_ui = (EditText) findViewById(R.id.EditPlayerPhone);


    }
    public void onValid (View v) {
        String playerName = playerName_ui.getText().toString();
        String playerPosition = player_position_ui.getText().toString();
        String playerMail = playerMail_ui.getText().toString();
        String playerPhone = playerPhone_ui.getText().toString();

        Intent intent = getIntent();
        intent.putExtra("playerName", playerName);
        intent.putExtra("playerPosition", playerPosition);
        intent.putExtra("playerMail", playerMail);
        intent.putExtra("playerPhone", playerPhone);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void onCancel (View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

}
