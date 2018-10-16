package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import fr.eseo.dis.godetgui.somanagerapp.data.JM;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchJuryProjects;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchPoster;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchProjects;

public class DetailsProjectsJMActivity extends AppCompatActivity {

    private String projectId;
    private String currentIdJury;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    //Recupération des textView
    private TextView champ_jur ;
    private TextView champ_descr ;
    private TextView champ_titre ;
    private TextView champ_tut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_projects_jm);
        //recupération de l'intent et de l'idJury passé en paramètre
        Intent intent = getIntent();
        this.currentIdJury = intent.getStringExtra("idJury");
        this.projectId = intent.getStringExtra("projectId");

        this.champ_jur = findViewById(R.id.champ_jur);
        this.champ_descr = findViewById(R.id.champ_descr);
        this.champ_titre = findViewById(R.id.champ_titre);
        this.champ_tut = findViewById(R.id.champ_tut);



        champ_jur.setText(this.currentIdJury);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        FetchJuryProjects fetchJuryProjects= new FetchJuryProjects(DetailsProjectsJMActivity.this, this.usernameSession, this.tokenSession, this.currentIdJury, this.projectId);
        fetchJuryProjects.execute();

        //FetchPoster fetchPoster = new FetchPoster(this, this.usernameSession, this.tokenSession, this.currentIdJury );
        //fetchPoster.execute();



    }

    public void getDataProjectsDetails(JSONObject JO) throws JSONException {

        JSONArray projectArray = JO.getJSONArray("projects");
        String title = "";
        String desc = "";
        String tutor = "";

        for ( int i =0; i<projectArray.length(); i++){
            System.out.println("-------------1---" +this.projectId);

            if( projectArray.getJSONObject(i).getString("projectId").equals(this.projectId)){
                desc = projectArray.getJSONObject(i).getString("descrip");
                title = projectArray.getJSONObject(i).getString("title");
                tutor = projectArray.getJSONObject(i).getJSONObject("supervisor").getString("forename")+" " + projectArray.getJSONObject(i).getJSONObject("supervisor").getString("1");


            }

        }
       // this.champ_descr.setText(desc);

        this.champ_titre.setText(title);
        this.champ_tut.setText(tutor);


    }

    }


