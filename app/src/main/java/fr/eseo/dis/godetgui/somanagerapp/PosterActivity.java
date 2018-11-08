package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PosterActivity extends AppCompatActivity {

    private String projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        Intent intent = getIntent();
        this.projectId = intent.getStringExtra("idProject");
        System.out.println("Project ID: "+this.projectId);


    }
}
