package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.Toast;


import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import fr.eseo.dis.godetgui.somanagerapp.Certificates.CertificatesPoster.SSLUtil;
import fr.eseo.dis.godetgui.somanagerapp.Certificates.TrustManager;
import okhttp3.OkHttpClient;

public class PosterActivity extends AppCompatActivity {

    private String projectId;
    private SharedPreferences myPrefs;
    private String usernameSession;
    private String tokenSession;
    private ImageView posterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_poster);
        super.onCreate(savedInstanceState);

        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.getApplicationContext());

        this.posterView = findViewById(R.id.poster);
        System.out.println("THIS.POSTERVIEW: "+this.posterView);


        Intent intent = getIntent();
        this.projectId = intent.getStringExtra("idProject");
        System.out.println("Project ID: "+this.projectId);

        //récupération des variables de sessions
        myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
        this.usernameSession = myPrefs.getString("USERNAME",null);
        this.tokenSession = myPrefs.getString("TOKEN",null);

        this.loadPoster(this.posterView, this.usernameSession, this.projectId, this.tokenSession);



    }



    private void loadPoster(ImageView view, String user, String idSujet, String token) {
        //String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/PNG_transparency_demonstration_1.png/280px-PNG_transparency_demonstration_1.png";
        String url = "https://192.168.4.248/pfe/webservice.php?q=POSTR&user="+user+"&proj="+idSujet+"&style=FULL&token="+token;
        final Context context = this.getApplicationContext();
        Picasso picasso = PicassoHttpsUtil.getPicasso(context);



        // Pour des informations sur Picasso voir http://square.github.io/picasso/
        PicassoHttpsUtil.getPicasso(context).load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("*************Affichage réussi*************");
                    }

                    @Override
                    public void onError(Exception e) {
                        // En cas d'erreur afficher un toast
                        // L'erreur n'est pas forcement du a la connexion réseau, mais dans
                        // le cas où on sait que l'url est correcte, c'est le plus probable
                        //Toast.makeText(context,
                        //        "Une connexion au réseau est requise pour afficher l'image !",
                        //        Toast.LENGTH_SHORT).show();
                        createDialogAlert();
                    }
                });
    }


    public void createDialogAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PosterActivity.this);

        alertDialogBuilder
                .setTitle("Error")
                .setMessage("No poster to display or bad internet connection")
                .setCancelable( false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}
