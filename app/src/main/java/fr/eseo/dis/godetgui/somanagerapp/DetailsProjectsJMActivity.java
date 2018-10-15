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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_projects_jm);
        //recupération de l'intent et de l'idJury passé en paramètre
        Intent intent = getIntent();
        this.currentIdJury = intent.getStringExtra("idJury");
        this.projectId = intent.getStringExtra("projectId");

        //Recupération des textView
        TextView champ_jur = findViewById(R.id.champ_jur);


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

    public static void getDataProjectsDetails(JSONObject JO) throws JSONException {


        JSONArray response = JO.getJSONArray("info");
        JSONObject infos = response.getJSONObject(0);
        String description = infos.getString("descrip");
        String title = infos.getString("title");
        String confid = infos.getString("confid");
        String id = infos.getString("projectId");

            System.out.println("------------"+id);
        if (id.equals(26)) {
           // ajout();
        }
        }

            public void ajout(){
            //this.champ_descr.setText("OUUIII");
            }
    }


