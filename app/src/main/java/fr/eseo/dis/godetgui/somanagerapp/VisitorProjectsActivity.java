package fr.eseo.dis.godetgui.somanagerapp;

        import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;
        import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProjectManager;
        import fr.eseo.dis.godetgui.somanagerapp.threads.FetchVisitorProjects;

public class VisitorProjectsActivity extends AppCompatActivity {

    public Button goToCreateVisitor;
    private Context context;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private List<String> projectsList;
    private ListView ListViewProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_projects);


        //récupération du contexte
        this.context = this.getApplicationContext();

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        //récupération de la ListView
        ListViewProjects = (ListView)findViewById(R.id.ListViewProjects);

        //Appel du webService
        FetchVisitorProjects fetchvisitorProjects = new FetchVisitorProjects(this, this.usernameSession, this.tokenSession);
        fetchvisitorProjects.execute();
    }

    public void getDataVisitorProjects(JSONObject JO) throws JSONException {
    System.out.println("TESTTTTTT");
        //Remplissage de la liste des jurys et du hasmamp qui stocke les id des juries en fonction de leur position dans la liste
        PseudoJuryProjectManager m = new PseudoJuryProjectManager(this);

        m.open();

        List<PseudoJuryProject> a = m.getPJ(1);


        JSONArray responseProjects = JO.getJSONArray("projects");
        final HashMap<Integer, String> hashMapId = new HashMap();
        //final HashMap<Integer, String> hashMapInfo = new HashMap();
        String Newligne=System.getProperty("line.separator");

        this.projectsList = new ArrayList<>();




        for(int i = 0; i<responseProjects.length(); i++) {

            if (Integer.parseInt(responseProjects.getJSONObject(i).getString("projectId"))==a.get(0).getIdProject() || Integer.parseInt(responseProjects.getJSONObject(i).getString("projectId"))==a.get(1).getIdProject() || Integer.parseInt(responseProjects.getJSONObject(i).getString("projectId"))==a.get(2).getIdProject() || Integer.parseInt(responseProjects.getJSONObject(i).getString("projectId"))==a.get(3).getIdProject() || Integer.parseInt(responseProjects.getJSONObject(i).getString("projectId"))==a.get(4).getIdProject()){
                    System.out.println("ROULEPOULE");

                    this.projectsList.add(i, "Project: " + responseProjects.getJSONObject(i).getString("projectId")
                            + Newligne + "Title: " + responseProjects.getJSONObject(i).getString("title"))
                    ;

            }
            else{
                this.projectsList.add(i, "No Access to project " + responseProjects.getJSONObject(i).getString("projectId") )
                ;
            }
            hashMapId.put(i, responseProjects.getJSONObject(i).getString("projectId"));

            //creation de l'adapter et association de l'adapter avec la listViewNObject(i).getString("title")
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projectsList);
            ListViewProjects.setAdapter(arrayAdapter);

            //Click sur un élément de la liste
            ListViewProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent goToDetailsProjectActivity = new Intent(VisitorProjectsActivity.this, DetailsProjectsVisitorActivity.class);
                    goToDetailsProjectActivity.putExtra("projectId", hashMapId.get(position));
                    startActivity(goToDetailsProjectActivity);

                }
            });




        }

        m.close();

    }
}
