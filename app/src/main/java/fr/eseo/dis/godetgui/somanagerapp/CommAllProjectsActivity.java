package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchMyJurys;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchProjects;

public class CommAllProjectsActivity extends AppCompatActivity {

    public Button buttonGoToProjects;
    private Context context;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private List<String> projectsList;
    private ListView ListViewProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_all_projects);


        //récupération du contexte
        this.context = this.getApplicationContext();

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        //récupération de la ListView
        ListViewProjects = (ListView)findViewById(R.id.ListViewProjects);

        //Appel du webService
        FetchProjects fetchProjects = new FetchProjects(this, this.usernameSession, this.tokenSession);
        fetchProjects.execute();
    }

    public void getDataProjects(JSONObject JO) throws JSONException {

        JSONArray responseProjects = JO.getJSONArray("projects");
        final HashMap<Integer, String> hashMapId = new HashMap();
        //final HashMap<Integer, String> hashMapInfo = new HashMap();
        String Newligne=System.getProperty("line.separator");

        this.projectsList = new ArrayList<>();

        //Remplissage de la liste des jurys et du hasmamp qui stocke les id des juries en fonction de leur position dans la liste
        for(int i = 0; i<responseProjects.length(); i++){
            this.projectsList.add(i,"Project: "+responseProjects.getJSONObject(i).getString("projectId")
                    + Newligne +"Title: "+responseProjects.getJSONObject(i).getString("title"))
            ;
            hashMapId.put(i,responseProjects.getJSONObject(i).getString("projectId"));
           // hashMapInfo.put(i, responseProjects.getJSONObject(i).getString("descrip"));

/*
                //Récupération des projets associés et stockage en BDD
                JSONArray projects = responseJuries.getJSONObject(i).getJSONObject("info").getJSONArray("projects");
                for(int j = 0; j<projects.length(); j++){
                    //Récupération
                    JSONObject projectJSON = projects.getJSONObject(j);

                    //Création d'un nouveau projet
                    Projects projectToAdd = new Projects();
                    projectToAdd.setIdProject((Integer.parseInt(projectJSON.getString("projectId"))));
                    projectToAdd.setNom(projectJSON.getString("title"));
                    projectToAdd.setConfidentialite(Integer.parseInt(projectJSON.getString("confid")));

                    //Ajout à la BDD
                SomanagerDatabase.getDatabase(this).projectsDao().insertProject(projectToAdd);
            } */
        }

        //creation de l'adapter et association de l'adapter avec la listView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,projectsList);
        ListViewProjects.setAdapter(arrayAdapter);





    }
}
