package fr.eseo.dis.godetgui.somanagerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuries;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuriesManager;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchLogon;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchRandomProject;

public class VisitorCreationActivity extends AppCompatActivity {
    private EditText visitor_login;
    private EditText visitor_password;
    private EditText visitor_mail;
    private EditText visitor_function;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private ArrayList<String> listIdRndProject = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_creation);
    }
    public void onClickBtn(View view) {
        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME", null);
        this.tokenSession = myPrefs.getString("TOKEN", null);

        FetchRandomProject fetchRandomProject = new FetchRandomProject(this, this.usernameSession, this.tokenSession);
        fetchRandomProject.execute();

        this.visitor_login = (EditText) findViewById(R.id.visitor_login);
        this.visitor_password = (EditText) findViewById(R.id.visitor_password);
        this.visitor_mail = (EditText) findViewById(R.id.visitor_mail);
        this.visitor_function = (EditText) findViewById(R.id.visitor_function);

        PseudoJuriesManager m = new PseudoJuriesManager(this);
        m.open();
        PseudoJuries a=m.getPJ(this.visitor_login.getText().toString());
        if( this.visitor_login.getText().toString().equals("") || this.visitor_password.getText().toString().equals("")){
            createDialogAlertEmpty();
        }else {
            if (a.getMdpPj().equals("")) {
                m.addPJ(new
                        PseudoJuries(0, this.visitor_login.getText().toString(), this.visitor_password.getText().toString(), this.visitor_function.getText().toString(), this.visitor_mail.getText().toString()));

                m.close();
                finish();
            } else {
                createDialogAlertUser();
            }
        }
        m.close();
    }

    public void createDialogAlertUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitorCreationActivity.this);
        alertDialogBuilder
                .setTitle("Error Login")
                .setMessage("User already exists")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void createDialogAlertEmpty() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitorCreationActivity.this);

        alertDialogBuilder
                .setTitle("Error Login & password")
                .setMessage("Empty Login or Password")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void getData(JSONObject JO) throws JSONException {
        JSONArray projectsArray = new JSONArray();
        projectsArray = JO.getJSONArray("projects");


        for (int i = 0; i < projectsArray.length(); i++) {
            listIdRndProject.add(i, projectsArray.getJSONObject(i).get("idProject").toString());
        }
        PseudoJuryProjectManager m = new PseudoJuryProjectManager(this);
        PseudoJuriesManager p = new PseudoJuriesManager(this);

        m.open();
        p.open();
        System.out.println("Liste des id des projets random");
        for (int i = 0; i < listIdRndProject.size(); i++) {
            System.out.println(listIdRndProject.get(i));
            System.out.println("france"+ p.getPJ(this.visitor_login.getText().toString()).getIdPseudoJuries());
            m.addPJ(new
                    PseudoJuryProject(0, p.getPJ(this.visitor_login.getText().toString()).getIdPseudoJuries(), Integer.parseInt(listIdRndProject.get(i)), "", ""));


        }
        m.close();
        p.close();



    }
}
