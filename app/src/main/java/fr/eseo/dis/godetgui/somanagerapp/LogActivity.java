package fr.eseo.dis.godetgui.somanagerapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchDataLogon;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchRole;

public class LogActivity extends AppCompatActivity {

    private Button btnConnexion;
    private String status;
    private EditText login;
    private String token;
    private String userConnected;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        this.context = this.getApplicationContext();



    }


    public void onClickBtn(View view){
        this.login = (EditText)findViewById(R.id.loginText);
        EditText password = (EditText)findViewById(R.id.passwordText);

        FetchDataLogon fetchDataLogon = new FetchDataLogon(this.context,this.login.getText().toString(), password.getText().toString());
        fetchDataLogon.execute();

    }



    /**
     * Récupération des données issues du webService LOGON
     * @param JO
     * @throws JSONException
     */
    //Impossibilité de récupérer le context de l'activity dans cette méthode ????
    public void getDataLogon(JSONObject JO, String user) throws JSONException {
        System.out.println("********************RESULTAT JSON: "+JO);
        String result = JO.getString("result");

        if (result.equals("OK") ){
            this.userConnected = user;
            this.token = JO.getString("token");
            this.fetchRole();

        }
        else {
            String error = JO.getString("error");
            //createDialogAlert();
        }

    }
    //Impossibilité de récupérer le context de l'activity dans cette méthode ????
    public void fetchRole(){
        //System.out.println("********************USER: "+userConnected);
        FetchRole fetchRole = new FetchRole(this.context,userConnected, token);
        fetchRole.execute();
    }

    public void getDataRole(JSONObject JO) throws JSONException {
        String result = JO.getString("info");

        System.out.println("INFO :"+result);
    }


    public void createDialogAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LogActivity.this);

        alertDialogBuilder
                .setTitle("Error Conection")
                .setMessage("Whould you like to try again?")
                .setCancelable( false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LogActivity.this, "Yes", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LogActivity.this, "No", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }



    }
