package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProjectManager;

public class DetailsProjectsVisitorActivity extends AppCompatActivity {

    private String projectId;
    private String currentIdJury;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    //Recupération des textView
    private TextView champ_descr ;
    private TextView champ_titre ;
    private TextView champ_tut;
    private ListView listViewStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_projects_visitor);
        //recupération de l'intent et de l'idJury passé en paramètre
        Intent intent = getIntent();
        this.currentIdJury = intent.getStringExtra("idJury");
        this.projectId = intent.getStringExtra("projectId");

        System.out.println("ID PROJECT: "+this.projectId);

        this.champ_descr = findViewById(R.id.champ_descr);
        this.champ_titre = findViewById(R.id.champ_titre);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);

        PseudoJuryProjectManager pseudoJuryProjectManger = new PseudoJuryProjectManager(this);

        for(int i = 0; i < pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).size(); i++ ){
            pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).get(i).getTitle();
            pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).get(i).getComment();
        }
        //pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId));

    }

    public void getDataProjectsDetailsVisitor(JSONObject JO) throws JSONException {
        ArrayList<String> listStudent = new ArrayList<>();
        JSONArray projectArray = JO.getJSONArray("projects");

        System.out.println("IT S ME"+projectArray);

        String title = "";
        String desc = "";
        String tutor = "";

        for ( int i =0; i<projectArray.length(); i++){

            if( projectArray.getJSONObject(i).getString("projectId").equals(this.projectId)){
                desc = projectArray.getJSONObject(i).getString("descrip");
                title = projectArray.getJSONObject(i).getString("title");
                tutor = projectArray.getJSONObject(i).getJSONObject("supervisor").getString("forename")+" " + projectArray.getJSONObject(i).getJSONObject("supervisor").getString("surname");
                for (int j = 0; j < projectArray.getJSONObject(i).getJSONArray("students").length(); j++) {
                    listStudent.add(j, projectArray.getJSONObject(i).getJSONArray("students").getJSONObject(j).getString("forename") +" "+ projectArray.getJSONObject(i).getJSONArray("students").getJSONObject(j).getString("surname"));
                }
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listStudent);
        this.listViewStudents.setAdapter(arrayAdapter);

        this.champ_descr.setText(desc);
        this.champ_titre.setText(title);
        this.champ_tut.setText(tutor);
    }
    public void goToMyProjects(View v){
        finish();
    }

    public void goToViewPoster(View v){
        Intent goToPosterActivity = new Intent(DetailsProjectsVisitorActivity.this, PosterActivity.class);
        goToPosterActivity.putExtra("idProject", this.projectId);
        startActivity(goToPosterActivity);
    }
}