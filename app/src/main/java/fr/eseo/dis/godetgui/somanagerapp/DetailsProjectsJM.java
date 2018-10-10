package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailsProjectsJM extends AppCompatActivity {

    public Button buttonGoToProjects2;

        public void init(){
            buttonGoToProjects2 = findViewById(R.id.buttonGoToProjects2);
            buttonGoToProjects2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent test = new Intent(DetailsProjectsJM.this,ProjectsJMActivity.class);
                    startActivity(test);
                }
            });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_projects_jm);
        init();

    }
}
