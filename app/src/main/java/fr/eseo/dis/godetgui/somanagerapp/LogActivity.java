package fr.eseo.dis.godetgui.somanagerapp;

import android.app.Dialog;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);



    }


    public void onClickBtn(View view){
        EditText login = (EditText)findViewById(R.id.loginText);
        EditText password = (EditText)findViewById(R.id.passwordText);

        FetchDataLogon fetchDataLogon = new FetchDataLogon(this.getApplicationContext(),login.getText().toString(), password.getText().toString());
        fetchDataLogon.execute();

    }

    public void onClickBtnTest(View view){
        FetchRole fetchRole = new FetchRole(this.getApplicationContext(),"alberpat", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE1MzkxODM5MjEsImp0aSI6IlpmTzlmeThJbDQwNjV3dldaSHgralF4b2Frb1ZWV0FWYnR6bFZhc3M3Z1E9IiwianNzIjoiMTkyLjE2OC40LjI0OCIsIm5iZiI6MTUzOTE4MzkyMSwiZXhwIjoxNTM5MTg0MjIxLCJkYXRhIjp7ImlkIjowLCJrZXkiOiJQQ3VKb0pLQU14SGxWWnc0OTF2bE9RPT0ifX0.1d7VIVbjXER9rfJH9JPVuW721cBtFOYEkpLSZca6cKinU62jEgS9z87sMgIKPNyTyGRAGa8q9jZI84xnuSbxrA");
        fetchRole.execute();
    }


    //q=MYINF, token

    public void getDataLogon(JSONObject JO) throws JSONException {
        String result = JO.getString("result");

        if (result.equals("OK") ){
            String token = JO.getString("token");
            //this.getDataRole();
            Intent test = new Intent(LogActivity.this ,JurysJMActivity.class);
            startActivity(test);



        }else {
            String error = JO.getString("error");
            //createDialogAlert();

        }

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
