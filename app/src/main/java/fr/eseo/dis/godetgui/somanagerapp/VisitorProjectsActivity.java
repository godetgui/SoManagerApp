package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuries;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuriesManager;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;
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
        String Newligne=System.getProperty("line.separator");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_projects);


        //récupération du contexte
        this.context = this.getApplicationContext();

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.uservisitor = myPrefs.getString("USERVISITOR",null);

        //récupération de la ListView
        ListViewProjects = (ListView)findViewById(R.id.ListViewProjects);

        this.projectsList = new ArrayList<>();

        //Appel de la base de donnée et récupération des projets liés à ce visiteur
        PseudoJuryProjectManager tablePJP = new PseudoJuryProjectManager(this);
        PseudoJuriesManager tablePJ = new PseudoJuriesManager(this);
        tablePJ.open();
        int idUser=tablePJ.getPJ(this.uservisitor).getIdPseudoJuries();
        tablePJ.close();




        for (int i=0; i< 5;i++){
            tablePJP.open();
            int id =tablePJP.getPJList(idUser).get(i).getIdProject();
            tablePJP.close();

            tablePJP.open();
            String title = tablePJP.getPJList(idUser).get(i).getTitle();
            tablePJP.close();


            this.projectsList.add(i, "Project: " + id
                    + Newligne + "Title: " + title)
            ;
        }

        tablePJP.open();

        System.out.println("test23"+tablePJP.getPJ(7).getTitle());
        // modification du nom de l'animal dont l'id est 1
        PseudoJuryProject a=tablePJP.getPJ(
                7);
        a.setGrade("12");
System.out.println("vvvvvvvvv"+a.getIdPseudoJuryProject());
        tablePJP.modPJ(a);

        tablePJP.close();


        //creation de l'adapter et association de l'adapter avec la listViewNObject(i).getString("title")
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projectsList);
        ListViewProjects.setAdapter(arrayAdapter);




    }


}