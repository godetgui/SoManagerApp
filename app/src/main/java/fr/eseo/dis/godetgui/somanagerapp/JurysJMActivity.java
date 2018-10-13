package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.eseo.dis.godetgui.somanagerapp.threads.FetchMyJurys;

public class JurysJMActivity extends AppCompatActivity {

    public Button buttonGoToProjects;
    private Context context;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private int jurysSize;
    private ArrayList<JSONObject> jurysList;



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



        FetchMyJurys fetchMyJurys = new FetchMyJurys(this, this.usernameSession, this.tokenSession);
        fetchMyJurys.execute();

    }

    public void getDataJurys(JSONObject JO) throws JSONException {
        System.out.println("********************RESULTAT JSON JURY: "+JO);

        JSONArray response = JO.getJSONArray("juries");
        jurysList = new ArrayList<>();

        //Remplissage de la liste des jurys
        for(int i = 0; i<response.length(); i++){
            this.jurysList.add(i,response.getJSONObject(i));
        }





        System.out.println("********************RESULTAT JURYLIST: "+this.jurysList);

        System.out.println("********************RESULTAT JSON PROJECTS: "+response);
        System.out.println("********************RESULTAT JSON Jury length: "+response.length());



    }
}

