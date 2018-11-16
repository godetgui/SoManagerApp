package fr.eseo.dis.godetgui.somanagerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;
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
    private TextView champ_current_comment;
    private TextView champ_current_note;
    private EditText champ_new_note;
    private EditText champ_new_comment;


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
        this.champ_current_note = findViewById(R.id.champ_current_note);
        this.champ_current_comment = findViewById(R.id.champ_current_comment);
        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);

        PseudoJuryProjectManager pseudoJuryProjectManger = new PseudoJuryProjectManager(this);
        pseudoJuryProjectManger.open();
        champ_titre.setText(pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).get(0).getTitle());
        pseudoJuryProjectManger.close();
        pseudoJuryProjectManger.open();
        champ_descr.setText(pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).get(0).getDescription());
        pseudoJuryProjectManger.close();
        pseudoJuryProjectManger.open();
        champ_current_note.setText(pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).get(0).getGrade());
        pseudoJuryProjectManger.close();

        pseudoJuryProjectManger.open();
        champ_current_comment.setText(pseudoJuryProjectManger.getPJProjectById(Integer.parseInt(this.projectId)).get(0).getComment());
        pseudoJuryProjectManger.close();



    }


    public void goToMyProjects(View v){

finish();

    }


    public void modifyGrade(View view) {
        //Appel de la base de donnée et récupération des projets liés à ce visiteur
        PseudoJuryProjectManager tablePJP = new PseudoJuryProjectManager(this);
        tablePJP.open();

        // modification du nom de l'animal dont l'id est 1
        PseudoJuryProject a=tablePJP.getPJ(
                Integer.parseInt(this.projectId));

        this.champ_new_note = (EditText) findViewById(R.id.champ_new_note);
        this.champ_new_comment = (EditText) findViewById(R.id.champ_new_comment);

        if(!this.champ_new_note.getText().toString().isEmpty()) {
            if(Integer.parseInt(this.champ_new_note.getText().toString()) <= 20){
                a.setGrade(this.champ_new_note.getText().toString());
            }else{
                createDialogAlertEmpty();

            }


        }
        if(!this.champ_new_comment.getText().toString().isEmpty()) {

            a.setComment(this.champ_new_comment.getText().toString());

        }
        tablePJP.modPJ(a);
        tablePJP.close();
        finish();

    }

    public void goToViewPoster(View v){
        Intent goToPosterActivity = new Intent(DetailsProjectsVisitorActivity.this, PosterActivity.class);
        goToPosterActivity.putExtra("idProject", this.projectId);
        startActivity(goToPosterActivity);
    }

    public void createDialogAlertEmpty() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DetailsProjectsVisitorActivity.this);

        alertDialogBuilder
                .setTitle("Error Grade or comment")
                .setMessage("Please Try again")
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