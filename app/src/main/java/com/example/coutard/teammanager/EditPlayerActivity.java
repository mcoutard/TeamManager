package com.example.coutard.teammanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by COUTARD on 31/12/2017.
 */

/*
* Cette classe permet de créer des joueurs et de les ajouter aux équipes déjà existantes.
* Elle peut aussi les éditer.
 */


public class EditPlayerActivity extends AppCompatActivity {

    private EditText playerName_ui;
    private EditText player_position_ui;
    private EditText playerMail_ui;
    private EditText playerPhone_ui;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        playerName_ui = findViewById(R.id.EditPlayerName);
        player_position_ui = findViewById(R.id.EditPlayerPosition);
        playerMail_ui = findViewById(R.id.EditPlayerMailAdress);
        playerPhone_ui = findViewById(R.id.EditPlayerPhone);

        /*
        Ce IF est très important , c'est lui qui nous permet de récupérer les données de "la couche
        du dessus" , dans notre ca on récupère les données du joueur que l'équipe possède déjà.
        Si on créer un nouveau joueur , rien ne s'affiche .
        Si on édite un joueur existant, les données déjà existantes s'affichent .
         */
        if (getIntent().hasExtra("player")) {
            Player player = getIntent().getParcelableExtra("player");

            playerName_ui.setText(player.getPlayerName());
            player_position_ui.setText(player.getPlayerPosition());
            playerMail_ui.setText(player.getPlayerMail());
            playerPhone_ui.setText(player.getPlayerPhone());
        }
    }


    /*
    Logiquement , lorsque l'on à entré toutes les données que l'on veut on clique sur le boutton
    valider et les données du joueurs sont envoyé à l'équipe.
     */
    public void onValid (View v) {

        if (!playerName_ui.getText().toString().matches("")){
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
        }else{
            playerName_ui.setError(getResources().getString(R.string.EnterPlayerName));
        }



    }

    //Lorsque l'on clique sur cancel, on quitte juste la vue.
    public void onCancel (View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

}
