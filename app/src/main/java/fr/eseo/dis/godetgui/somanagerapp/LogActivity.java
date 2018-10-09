package fr.eseo.dis.godetgui.somanagerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchDataLogon;

public class LogActivity extends AppCompatActivity {

    private Button btnConnexion;



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

    //context.getRessources().openRawRessources(cobntext.getRessources.getIdentifier(R.raw.chain)

    public static void getData(String data){
        Log.d("LogActivity","Résultat du JSON file: "+ data);

    }









}
