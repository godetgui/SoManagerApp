package fr.eseo.dis.godetgui.somanagerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuries;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuriesManager;
import fr.eseo.dis.godetgui.somanagerapp.data.PseudoJuryProject;
import fr.eseo.dis.godetgui.somanagerapp.threads.FetchLogon;

public class VisitorCreationActivity extends AppCompatActivity {
    private EditText visitor_login;
    private EditText visitor_password;
    private EditText visitor_mail;
    private EditText visitor_function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_creation);
    }
    public void onClickBtn(View view) {

        this.visitor_login = (EditText) findViewById(R.id.visitor_login);
        this.visitor_password = (EditText) findViewById(R.id.visitor_password);
        this.visitor_mail = (EditText) findViewById(R.id.visitor_mail);
        this.visitor_function = (EditText) findViewById(R.id.visitor_function);

        PseudoJuriesManager m = new PseudoJuriesManager(this);
        m.open();
        PseudoJuries a=m.getPJ(this.visitor_login.getText().toString());
        if( this.visitor_login.getText().toString().equals("") || this.visitor_password.getText().toString().equals("")){
            createDialogAlertEmpty();
        }else {
            if (a.getMdpPj().equals("")) {
                m.addPJ(new
                        PseudoJuries(0, this.visitor_login.getText().toString(), this.visitor_password.getText().toString(), this.visitor_function.getText().toString(), this.visitor_mail.getText().toString()));
                m.close();
                finish();
            } else {
                createDialogAlertUser();
            }
        }
        m.close();
    }

    public void createDialogAlertUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitorCreationActivity.this);
        alertDialogBuilder
                .setTitle("Error Login")
                .setMessage("User already exists")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void createDialogAlertEmpty() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VisitorCreationActivity.this);

        alertDialogBuilder
                .setTitle("Error Login & password")
                .setMessage("Empty Login or Password")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
