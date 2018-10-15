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

import fr.eseo.dis.godetgui.somanagerapp.data.Projects;

public class ProjectsJMActivity extends AppCompatActivity {

    public Button buttonGoToJurys;
    private String currentIdJury;
    private String currentInfo;
    private Context context;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private ListView listViewProjects;
    private JSONObject currentInfoJSONObject;
    String Newligne=System.getProperty("line.separator");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_jm);

        //recupération de l'intent et de l'idJury passé en paramètre
        Intent intent = getIntent();
        this.currentIdJury = intent.getStringExtra("idJury");
        this.currentInfo = intent.getStringExtra("info");
        System.out.println("DANS PROJECTJMACTIVITY, IDJURY: "+this.currentIdJury);
        System.out.println("DANS PROJECTJMACTIVITY, INFO: "+this.currentInfo);

        //récupération du contexte
        this.context = this.getApplicationContext();

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        //récupération de la ListView
        listViewProjects = (ListView)findViewById(R.id.listViewProjects);


        //Partitionnement du string en JSONObject
        try {
            this.currentInfoJSONObject = new JSONObject(this.currentInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("DANS PROJECTJMACTIVITY, INFOJSONARRAY: "+currentInfoJSONObject);

        try {
            this.getProjects(this.currentInfoJSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getProjects(JSONObject JO) throws JSONException {

        final HashMap<Integer, String> hashMapId = new HashMap();
        final String idJury = this.currentIdJury;

        JSONArray projectsArray = JO.getJSONArray("projects");
        ArrayList<String> listProjects = new ArrayList<>();
        for(int i =0; i<projectsArray.length(); i++){
            Projects project = new Projects();
            project.setIdProject(projectsArray.getJSONObject(i).getInt("projectId"));
            project.setNom(projectsArray.getJSONObject(i).getString("title"));
            project.setConfidentialite(projectsArray.getJSONObject(i).getInt("confid"));
            project.setPoster(projectsArray.getJSONObject(i).getBoolean("poster"));
            listProjects.add(i,project.toString());
            hashMapId.put(i,projectsArray.getJSONObject(i).getString("projectId"));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listProjects);
        listViewProjects.setAdapter(arrayAdapter);

        //Click sur un élément de la liste
        listViewProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent goToDetailsProjectActivity = new Intent(ProjectsJMActivity.this, DetailsProjectsJMActivity.class);
                goToDetailsProjectActivity.putExtra("projectId", hashMapId.get(position));
                goToDetailsProjectActivity.putExtra("idJury", idJury);
                startActivity(goToDetailsProjectActivity);

            }
        });
    }




}
