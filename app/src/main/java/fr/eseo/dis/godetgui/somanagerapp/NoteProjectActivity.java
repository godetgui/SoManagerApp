package fr.eseo.dis.godetgui.somanagerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchJMProjects;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchNotes;
import fr.eseo.dis.godetgui.somanagerapp.threads.SendNote;

public class NoteProjectActivity extends AppCompatActivity {

    private String currentIdStudent;
    private String currentIdProject;
    private EditText champ_new_note;
    private TextView champ_current_note;
    private TextView champ_avg_note;

    private String averageNote;
    private String currentNote;

    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;


    //PB surement ici
    //Voir si les id des students sont bien transmis et bien transmis dans la requete dans le onClickbtnSend()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_project);

        //recupération de l'intent et de l'idStudent passé en paramètre
        Intent intent = getIntent();
        System.out.println("INTENT" + intent);
        this.currentIdStudent = intent.getStringExtra("idStudent");
        this.currentIdProject = intent.getStringExtra("idProject");


        //Récupération des views
        this.champ_new_note = findViewById(R.id.champ_new_note);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        this.champ_avg_note = (TextView) findViewById(R.id.champ_avg_note);
        this.champ_current_note = (TextView) findViewById(R.id.champ_current_note);


        //Appel du webService
        FetchNotes fetchnotes = new FetchNotes(this, this.currentIdProject,this.usernameSession, this.tokenSession);
        fetchnotes.execute();


    }

    public void onClickbtnSend(View v){
        if (Integer.parseInt(this.champ_new_note.getText().toString())<=20){
        SendNote sendNote = new SendNote(this,this.usernameSession, this.tokenSession, this.currentIdProject, this.champ_new_note.getText().toString(), this.currentIdStudent);
        sendNote.execute();
        }
        else {
            createDialogAlert();
        }

    }


    public void getDataNotes(JSONObject JO) throws JSONException {

        JSONArray notesArray = JO.getJSONArray("notes");

        if(JO.getString("result").equals("OK")){
            for (int i = 0; i < notesArray.length(); i++) {

                if( notesArray.getJSONObject(i).getString("userId").equals(this.currentIdStudent)){


                    averageNote = notesArray.getJSONObject(i).getString("avgNote");
                    currentNote = notesArray.getJSONObject(i).getString("mynote");

                }
            }
        }

        this.champ_avg_note.setText(averageNote);
        this.champ_current_note.setText(currentNote);
    }

    public void getData(JSONObject JO) throws JSONException {


        if(JO.getString("result").equals("OK")){
            finish();
        }

    }



    public void createDialogAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NoteProjectActivity.this);

        alertDialogBuilder
                .setTitle("Invalid Note")
                .setMessage("0 <= Note =< 20")
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
