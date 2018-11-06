package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchJuryProjects;

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
    private ListView listViewStudents;



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
        this.listViewStudents = findViewById(R.id.listViewStudents);



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
        ArrayList<String> listStudent = new ArrayList<>();
        final HashMap<Integer, String> hashMapIdStudent = new HashMap();
        final HashMap<Integer, String> hashMapIdProject = new HashMap();


        for (int i = 0; i < projectArray.length(); i++) {
            System.out.println("-------------1---" + this.projectId);

            if( projectArray.getJSONObject(i).getString("projectId").equals(this.projectId)){
                desc = projectArray.getJSONObject(i).getString("descrip");
                title = projectArray.getJSONObject(i).getString("title");
                tutor = projectArray.getJSONObject(i).getJSONObject("supervisor").getString("forename") + " " + projectArray.getJSONObject(i).getJSONObject("supervisor").getString("surname");
                hashMapIdProject.put(i, this.projectId);
                //Récupération des JSONObjects des students
                for (int j = 0; j < projectArray.getJSONObject(i).getJSONArray("students").length(); j++) {
                    System.out.println("Taille du array STUDENTS: " + projectArray.getJSONObject(i).getJSONArray("students").length());
                    listStudent.add(j, projectArray.getJSONObject(i).getJSONArray("students").getJSONObject(j).getString("forename") + projectArray.getJSONObject(i).getJSONArray("students").getJSONObject(j).getString("surname"));
                    hashMapIdStudent.put(j, projectArray.getJSONObject(i).getJSONArray("students").getJSONObject(j).getString("userId"));
                }


            }

        }

        //
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listStudent);
        this.listViewStudents.setAdapter(arrayAdapter);

        this.champ_titre.setText(title);
        this.champ_tut.setText(tutor);
        this.champ_descr.setText(desc);
        System.out.println("titre: "+ this.champ_titre.getText().toString());
        System.out.println("tutor: "+ this.champ_titre.getText().toString());
        System.out.println("description: "+ this.champ_titre.getText().toString());


        this.listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent goToNoteProjectActivity = new Intent(DetailsProjectsJMActivity.this, NoteProjectActivity.class);

                goToNoteProjectActivity.putExtra("idStudent", hashMapIdStudent.get(position));
                System.out.println("Dans DPJMA.java: "+hashMapIdProject.get(0));
                goToNoteProjectActivity.putExtra("idProject", hashMapIdProject.get(0));
                startActivity(goToNoteProjectActivity);


            }
        });

    }






    public void goToMyProjects(View v){
        finish();
        //Click sur un élément de la liste


    }


}


