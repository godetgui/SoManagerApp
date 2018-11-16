package fr.eseo.dis.godetgui.somanagerapp;

import android.app.Dialog;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuriesManager;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProjectManager;

public class NotePseudoJuriesActivity extends AppCompatActivity {

    public Button goToCreateVisitor;
    private Context context;
    private SharedPreferences myPrefs;
    private String username;
    private String tokenSession;
    private List<String> projectsList;
    private ListView ListViewProjects;
    final ArrayList<Integer> data = new ArrayList<>();
     int idP =0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pseudo_juries);

        String Newligne=System.getProperty("line.separator");
        final HashMap<Integer, String> hashMapId = new HashMap();
        //récupération du contexte
        this.context = this.getApplicationContext();

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.username = myPrefs.getString("USERNAME",null);

        //récupération de la ListView
        ListViewProjects = (ListView)findViewById(R.id.ListViewNotes);


        this.projectsList = new ArrayList<>();

        //Appel de la base de donnée et récupération de tous les projets
        PseudoJuryProjectManager tablePJP = new PseudoJuryProjectManager(this);
        PseudoJuriesManager tablePJ = new PseudoJuriesManager(this);
        tablePJP.open();

        int sizePJ = tablePJP.getPJListAll().size();
        tablePJP.close();
        for(int i=0; i< sizePJ;i++){
            tablePJP.open();
           int projectId = tablePJP.getPJListAll().get(i).getIdProject();

           if(!data.contains(projectId)){
               data.add(projectId);
           }
            tablePJP.close();

        }
        final HashMap<Integer, Integer> hashMapProjectId = new HashMap<>();

        Collections.sort(data);
            for(int i=0; i< data.size();i++){
                idP = data.get(i);
                tablePJP.open();
                String nomProjet = tablePJP.getPJ(data.get(i)).getTitle();
                tablePJP.close();
                hashMapProjectId.put(i,data.get(i));
                this.projectsList.add( i,"Project: " + data.get(i) +
                        Newligne + "    Title: " + nomProjet
                )
                ;



                //creation de l'adapter et association de l'adapter avec la listViewNObject(i).getString("title")
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projectsList);
                ListViewProjects.setAdapter(arrayAdapter);
                //Click sur un élément de la liste
                ListViewProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        createDialogAlert(hashMapProjectId.get(position));

                    }
                });
            }



    }

    public void createDialogAlert(int idProject) {

        //Appel de la base de donnée et récupération de tous les projets
        PseudoJuryProjectManager tablePJP = new PseudoJuryProjectManager(this);
        PseudoJuriesManager tablePJ = new PseudoJuriesManager(this);


        // CharSequence colors[] = new CharSequence[] {"red", "green", "blue", "black"};

        List<String> listItems = new ArrayList<>();


        tablePJP.open();
        int size = tablePJP.getPJProjectById(idProject).size();
        tablePJP.close();
        for(int i=0; i< size;i++){

            tablePJP.open();
            int idPs = tablePJP.getPJProjectById(idProject).get(i).getIdPseudoJury();

            tablePJP.close();


            tablePJ.open();
            String loginPj = String.valueOf(tablePJ.getPJById(idPs).getLoginPj());

            tablePJ.close();

            tablePJP.open();
            String gradePJ = String.valueOf(tablePJP.getPJProjectById(idProject).get(i).getGrade());
            tablePJP.close();

            tablePJP.open();
            String commentPj = tablePJP.getPJProjectById(idProject).get(i).getComment();
            tablePJP.close();

            if( !gradePJ.isEmpty() && !commentPj.isEmpty()) {
                listItems.add(loginPj + " : " + gradePJ + " / " + commentPj);
            }

        }


        tablePJP.close();

        final CharSequence[] charSequenceItems = listItems.toArray(new CharSequence[listItems.size()]);


        AlertDialog.Builder builder = new AlertDialog.Builder(NotePseudoJuriesActivity.this);
        builder.setTitle("Grades & Comments :");
        builder.setItems(charSequenceItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
            }
        });
        builder.show();

    }




}
