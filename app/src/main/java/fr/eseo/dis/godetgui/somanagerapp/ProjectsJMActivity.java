package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProjectsJMActivity extends AppCompatActivity {

    public Button buttonGoToJurys;

    public void init(){
        buttonGoToJurys = findViewById(R.id.buttonGoToJurys);
        buttonGoToJurys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(ProjectsJMActivity.this,JurysJMActivity.class);
                startActivity(test);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_jm);
        init();
    }
}
