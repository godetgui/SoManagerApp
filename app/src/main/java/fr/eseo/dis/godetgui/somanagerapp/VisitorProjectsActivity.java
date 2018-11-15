package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProjectManager;

public class VisitorProjectsActivity extends AppCompatActivity {

    public Button goToCreateVisitor;
    private Context context;
    private SharedPreferences myPrefs;
    private String uservisitor;
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
        this.uservisitor = myPrefs.getString("USERVISITOR",null);

        //récupération de la ListView
        ListViewProjects = (ListView)findViewById(R.id.ListViewProjects);
        //Appel de la base de donnée et récupération des projets liés à ce visiteur
        PseudoJuryProjectManager m = new PseudoJuryProjectManager(this);

        m.open();

        m.close();
    }


}