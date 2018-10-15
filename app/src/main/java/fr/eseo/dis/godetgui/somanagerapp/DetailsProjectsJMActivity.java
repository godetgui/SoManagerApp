package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchPoster;

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
        this.projectId = intent.getStringExtra("idJury");
        this.currentIdJury = intent.getStringExtra("projectId");
        System.out.println("DANS DetailsProjectsJMActivity, IDJURY: "+this.currentIdJury);
        System.out.println("DANS DetailsProjectsJMActivity, IDPROJECT: "+this.projectId);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        //FetchPoster fetchPoster = new FetchPoster(this, this.usernameSession, this.tokenSession, this.currentIdJury );
        //fetchPoster.execute();



    }
}
