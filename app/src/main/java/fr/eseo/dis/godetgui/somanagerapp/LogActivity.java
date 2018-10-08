package fr.eseo.dis.godetgui.somanagerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


        ;import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LogActivity extends AppCompatActivity {

    private Button btnConnexion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    }

    public void onClickButton(View view){
        EditText login = (EditText)findViewById(R.id.loginText);
        EditText password = (EditText)findViewById(R.id.passwordText);
        Log.d("LogActivity","SendInfo() appellée");

        Log.d("LogActivity","LOGIN: "+login.getText().toString());
        Log.d("LogActivity","MDP: "+password.getText());

        Log.d("LogActivity","Test GitHub 2: "+password.getText());





        //https://192.168.4.248/www/pfe/webservice.php?q=LOGON&user=alberpat&pass=w872o32HkYAO
        //'https://jsonplaceholder.typicode.com/todos/1'




    }
}
