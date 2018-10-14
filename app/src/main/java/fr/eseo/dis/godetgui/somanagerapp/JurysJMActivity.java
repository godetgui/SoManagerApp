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

import fr.eseo.dis.godetgui.somanagerapp.data.Projects;
import fr.eseo.dis.godetgui.somanagerapp.data.ProjectsDao;
import fr.eseo.dis.godetgui.somanagerapp.data.SomanagerDatabase;
import fr.eseo.dis.godetgui.somanagerapp.data.Tutors;
import fr.eseo.dis.godetgui.somanagerapp.data.TutorsDao;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchMyJurys;

public class JurysJMActivity extends AppCompatActivity {

    public Button buttonGoToProjects;
    private Context context;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private List<String> jurysList;
    private ListView listViewJuries;



/*        public void init(){
            buttonGoToProjects = findViewById(R.id.buttonGoToProjects);
            buttonGoToProjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent test = new Intent(JurysJMActivity.this,ProjectsJMActivity.class);
                    startActivity(test);
                }
            });
        }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurys_jm);

        //récupération du contexte
        this.context = this.getApplicationContext();

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        //récupération de la ListView
        listViewJuries = (ListView)findViewById(R.id.listViewJuries);

        //Appel du webService
        FetchMyJurys fetchMyJurys = new FetchMyJurys(this, this.usernameSession, this.tokenSession);
        fetchMyJurys.execute();

    }

    public void getDataJurys(JSONObject JO) throws JSONException {

        JSONArray responseJuries = JO.getJSONArray("juries");
        final HashMap<Integer, String> hashMapId = new HashMap();
        final HashMap<Integer, String> hashMapInfo = new HashMap();

        this.jurysList = new ArrayList<>();

        //Remplissage de la liste des jurys et du hasmamp qui stocke les id des juries en fonction de leur position dans la liste
        for(int i = 0; i<responseJuries.length(); i++){
            this.jurysList.add(i,"Jury: "+responseJuries.getJSONObject(i).getString("idJury")+", Date: "+responseJuries.getJSONObject(i).getString("date"));
            hashMapId.put(i,responseJuries.getJSONObject(i).getString("idJury"));
            hashMapInfo.put(i, responseJuries.getJSONObject(i).getString("info"));

            //Récupération des projets associés et stockage en BDD
/*            JSONArray infosArray = responseJuries.getJSONObject(i).getJSONArray("info");
            for(int j = 0; j<infosArray.length(); j++){
                //Récupération
                JSONObject projectJSON = infosArray.getJSONObject(j);

                //Création d'un nouveau projet
                Projects projectToAdd = new Projects();
                projectToAdd.setIdProject((Integer.parseInt(projectJSON.getString("projectId"))));
                projectToAdd.setNom(projectJSON.getString("title"));
                projectToAdd.setConfidentialite(Integer.parseInt(projectJSON.getString("confid")));

                //Ajout à la BDD
                SomanagerDatabase.getDatabase(this).projectsDao().insertProject(projectToAdd);
            }*/
        }

        //creation de l'adapter et association de l'adapter avec la listView
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,jurysList);
        listViewJuries.setAdapter(arrayAdapter);


        //Click sur un élément de la liste
        listViewJuries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("AU CLICK, id: "+id);
                System.out.println("AU CLICK, position: "+position);
                System.out.println("AU CLICK, view: "+view);
                System.out.println("ID DU JURY CLIQUÉ: "+hashMapId.get(position));

                Intent goToProjectJMActivity = new Intent(JurysJMActivity.this, ProjectsJMActivity.class);
                goToProjectJMActivity.putExtra("idJury", hashMapId.get(position));
                goToProjectJMActivity.putExtra("info", hashMapInfo.get(position));

                startActivity(goToProjectJMActivity);

            }
        });



    }
}

