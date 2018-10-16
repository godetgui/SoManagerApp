package fr.eseo.dis.godetgui.somanagerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import fr.eseo.dis.godetgui.somanagerapp.threads.SendNote;

public class NoteProjectActivity extends AppCompatActivity {

    private String currentIdStudent;
    private String currentIdProject;
    private EditText champ_note;

    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_project);

        //recupération de l'intent et de l'idStudent passé en paramètre
        Intent intent = getIntent();
        this.currentIdStudent = intent.getStringExtra("idStudent");
        this.currentIdProject = intent.getStringExtra("idProject");

        System.out.println("CURRENT ID STUDENT: "+this.currentIdStudent);
        System.out.println("CURRENT ID PROJECT: "+this.currentIdProject);

        //Récupération des views
        this.champ_note = findViewById(R.id.champ_note);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);





    }

    public void onClickbtnSend(View v){

        SendNote sendNote = new SendNote(this,this.usernameSession, this.tokenSession, this.currentIdProject, this.champ_note.getText().toString(), this.currentIdStudent);
        sendNote.execute();
    }


    public void getData(JSONObject JO) throws JSONException {

        if(JO.getString("result").equals("OK")){
            finish();
        }
        else{
            createDialogAlert(JO.getString("error"));
        }

        System.out.println("RESULTAT DE LA REQUETE"+JO.getString("result"));
        System.out.println("RESULTAT DE LA REQUETE API"+JO.getString("api"));




    }

    public void createDialogAlert(String logError){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NoteProjectActivity.this);

        alertDialogBuilder
                .setTitle("Error")
                .setMessage(logError)
                .setCancelable( false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
