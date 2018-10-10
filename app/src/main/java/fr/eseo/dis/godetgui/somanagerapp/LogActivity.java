package fr.eseo.dis.godetgui.somanagerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    public static void getData(JSONObject JO) throws JSONException {
        String result = JO.getString("result");

    }


}
