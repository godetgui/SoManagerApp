package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.json.JSONObject;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchAllJurys;

public class JurysJMActivity extends AppCompatActivity {

    public Button buttonGoToProjects;
    private Context context;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;



/*        public void init(){
            buttonGoToProjects = findViewById(R.id.buttonGoToProjects);
            buttonGoToProjects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent test = new Intent(JurysJMActivity.this,ProjectsJMActivity.class);
                    startActivity(test);
                }
            });
        }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurys_jm);
        this.context = this.getApplicationContext();
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);
        System.out.println("*****************DANS JURYJMACTIVITY, USERNAME: "+usernameSession);
        System.out.println("*****************DANS JURYJMACTIVITY, TOKEN: "+tokenSession);



        FetchAllJurys fetchAllJurys = new FetchAllJurys(this, this.usernameSession, this.tokenSession);
        fetchAllJurys.execute();

    }

    public void getDataJurys(JSONObject JO){

        System.out.println("********************RESULTAT JSON JURY: "+JO);

    }
}

