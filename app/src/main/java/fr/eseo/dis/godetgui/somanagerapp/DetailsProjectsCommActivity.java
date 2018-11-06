package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchCommProjectsDetails;

public class DetailsProjectsCommActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_details_projects_comm);
        //recupération de l'intent et de l'idJury passé en paramètre
        Intent intent = getIntent();
        this.currentIdJury = intent.getStringExtra("idJury");
        this.projectId = intent.getStringExtra("projectId");

        System.out.println("ID PROJECT: "+this.projectId);

        this.champ_jur = findViewById(R.id.champ_jur);
        this.champ_descr = findViewById(R.id.champ_descr);
        this.champ_titre = findViewById(R.id.champ_titre);
        this.champ_tut = findViewById(R.id.champ_tut);

        champ_jur.setText(this.currentIdJury);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        FetchCommProjectsDetails fetchCommProjectsDetails= new FetchCommProjectsDetails(DetailsProjectsCommActivity.this, this.usernameSession, this.tokenSession);
        fetchCommProjectsDetails.execute();


    }


    public void getDataProjectsDetailsComm(JSONObject JO) throws JSONException {
        System.out.println("MANGER" + JO);
        JSONArray projectArray = JO.getJSONArray("projects");


        System.out.println("IT S ME"+projectArray);

        String title = "";
        String desc = "";
        String tutor = "";

        System.out.println("JE SUIS LA" + projectArray.length());

        for ( int i =0; i<projectArray.length(); i++){
            System.out.println("TEST APRES FOR"+ projectArray.getJSONObject(i).getString("projectId"));

            if( projectArray.getJSONObject(i).getString("projectId").equals(this.projectId)){
                desc = projectArray.getJSONObject(i).getString("descrip");
                title = projectArray.getJSONObject(i).getString("title");
                tutor = projectArray.getJSONObject(i).getJSONObject("supervisor").getString("forename")+" " + projectArray.getJSONObject(i).getJSONObject("supervisor").getString("surname");

            }

        }

        this.champ_descr.setText(desc);
        this.champ_titre.setText(title);
        this.champ_tut.setText(tutor);

    }

    public void goToMyProjects(View v){
        finish();
    }



}


