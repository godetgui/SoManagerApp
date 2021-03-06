package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchProjects;

public class CommAllProjectsActivity extends AppCompatActivity {

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


        for(int i = 0; i<responseProjects.length(); i++) {
            if (responseProjects.getJSONObject(i).getString("confid").equals("0")) {


                this.projectsList.add(i, "Project: " + responseProjects.getJSONObject(i).getString("projectId")
                        + Newligne + "Title: " + responseProjects.getJSONObject(i).getString("title"))
                ;
            }else{
                this.projectsList.add(i, "Confidential project")
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


                    Intent goToDetailsProjectActivity = new Intent(CommAllProjectsActivity.this, DetailsProjectsCommActivity.class);
                    goToDetailsProjectActivity.putExtra("projectId", hashMapId.get(position));
                    startActivity(goToDetailsProjectActivity);


            }
        });



        }

    }

    public void goToCreateVisitor(View v){
        if(myPrefs.getString("USERNAME",null).equals("jpo")) {
        Intent goToCreateVisitor = new Intent(CommAllProjectsActivity.this, VisitorCreationActivity.class);
        startActivity(goToCreateVisitor);
        }else{
            createDialogAlert();
        }
    }

    public void goSeePJNotes(View v){
        System.out.println("jeanne"+myPrefs.getString("USERNAME",null));
        if(myPrefs.getString("USERNAME",null).equals("jpo")) {

            Intent goSeePJNotes = new Intent(CommAllProjectsActivity.this, NotePseudoJuriesActivity.class);
        startActivity(goSeePJNotes);
        }else{
            createDialogAlert();
        }
    }

    public void createDialogAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CommAllProjectsActivity.this);

        alertDialogBuilder
                .setTitle("Error Access")
                .setMessage("You can't go here")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
