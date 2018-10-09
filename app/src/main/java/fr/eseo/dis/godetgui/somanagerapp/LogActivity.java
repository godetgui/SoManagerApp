package fr.eseo.dis.godetgui.somanagerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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


        Log.d("LogActivity","MDP HASHE: "+HashPassword(password.getText().toString()));








        //https://192.168.4.248/www/pfe/webservice.php?q=LOGON&user=alberpat&pass=w872o32HkYAO
        //'https://jsonplaceholder.typicode.com/todos/1'


    }

    public String HashPassword(String passwordToHash){
        //String passwordToHash = "password";
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
        //System.out.println(generatedPassword);

    }





}
