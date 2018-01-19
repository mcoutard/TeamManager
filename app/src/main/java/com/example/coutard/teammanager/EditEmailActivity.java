package com.example.coutard.teammanager;

/**
 * Created by COUTARD on 12/01/2018.
 */

/**
 * Cette classe s'occuppe de lorsque l'on édite un mail.
 * Lorsque que l'on cliquera sur send, le message sera envoyé à tous les membres de l'équipe qui ont
 * fournit leurs adresse mail
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditEmailActivity extends Activity {

    Button buttonSend;
    EditText Subject;
    EditText Message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        Subject =  findViewById(R.id.EditTextEmailSubject);
        Message =  findViewById(R.id.EditTextEmailMessage);


        //Ajout du boutton CANCEL
        Button cancelButton =  findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        //Ajout du boutton SEND
        buttonSend =  findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {sendMail(); }
        });
    }

    /*
     *Cette méthode nous permet d'envoyer des mails à toute l'équipe .
     *Pour cela on utilise tout bêtement un Intent
     */

    public void sendMail(){
        if (!Subject.getText().toString().matches("") && !Message.getText().toString().matches("")){
            String subject = Subject.getText().toString();
            String message = Message.getText().toString();

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{getMails()});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, message);

            //c'est ce qui nous permet de choisir le client mail.
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email,getResources().getString(R.string.ChooseAMailClient)));
            finish();
        }

        if(Subject.getText().toString().matches("")){
            Subject.setError(getResources().getString(R.string.EnterYourSubject));
        }
        if(Message.getText().toString().matches("")){
            Subject.setError(getResources().getString(R.string.EnterYourMessage));
        }

    }



    /*
    * Cette méthode permet de récupérer tous les mails connus de l'équipe et de les mettre dans un
    * String ( ici teamMails ) et ensuite ce string correspondra à l'adresse mail à envoyer.
    * Le client mail sait décomposer le string tout seul .
    */
    public String getMails(){
        if (getIntent().hasExtra("team")) {

            Team team = getIntent().getParcelableExtra("team");
            String teamMails = "" ;
            for (int i = 0; i < team.getTeam().size(); i ++  ){
                if (!team.getTeam().get(i).getPlayerMail().equals("")) {
                    if(teamMails == ""){
                        teamMails = teamMails + team.getTeam().get(i).getPlayerMail();
                    }else{
                        teamMails = teamMails + ";"+team.getTeam().get(i).getPlayerMail();
                    }

                }
            }

            return teamMails;

        }else{
            System.out.println("Sorry but no team has bean chosen");
            return null;
        }

    }



}