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


    //q=MYINF, token

    public void getData(JSONObject JO) throws JSONException {
        String result = JO.getString("result");

        if (result.equals("OK") ){
            String token = JO.getString("token");
            this.getRole();
            Intent test = new Intent(LogActivity.this ,JurysJMActivity.class);
            startActivity(test);



        }else {
            String error = JO.getString("error");
            //createDialogAlert();



        }

    }

    public void getRole(){

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
