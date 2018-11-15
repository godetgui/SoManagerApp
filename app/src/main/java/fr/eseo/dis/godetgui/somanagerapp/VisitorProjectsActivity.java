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

        //récupération de la ListView
        ListViewProjects = (ListView)findViewById(R.id.ListViewProjects);

        //Appel de la base de donnée et récupération des projets liés à ce visiteur

    }


}
