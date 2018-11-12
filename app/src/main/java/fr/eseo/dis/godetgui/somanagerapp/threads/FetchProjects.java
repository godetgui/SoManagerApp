package fr.eseo.dis.godetgui.somanagerapp.threads;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import fr.eseo.dis.godetgui.somanagerapp.Certificates.TrustManager;
import fr.eseo.dis.godetgui.somanagerapp.CommAllProjectsActivity;
import fr.eseo.dis.godetgui.somanagerapp.VisitorCreationActivity;

public class FetchProjects extends AsyncTask<Void, Void, Void> {

    String data = "";
    JSONObject JO;
    String user;
    String token;

    CommAllProjectsActivity commAllProjectsActivity;
    VisitorCreationActivity visitorCreationActivity;


    public FetchProjects(CommAllProjectsActivity commAllProjectsActivity, String user, String token){
        this.commAllProjectsActivity = commAllProjectsActivity;
        this.user = user;
        this.token = token;
    }

    public FetchProjects(VisitorCreationActivity visitorCreationActivity, String user, String token){
        this.visitorCreationActivity = visitorCreationActivity;
        this.user = user;
        this.token = token;
    }



    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        try{
            trustManager.getCertificate(this.commAllProjectsActivity.getApplicationContext());
        } catch(NullPointerException e) {
            trustManager.getCertificate(this.visitorCreationActivity.getApplicationContext());
        }

        try {

            URL url =  new URL("https://192.168.4.248/pfe/webservice.php?q=LIPRJ&user="+user+"&token="+token);

            //Créer une connection
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(trustManager.getSSLContext().getSocketFactory());
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //Lecture de la réponse et stockage dans un JSONObject
            String line="";
            while(line != null){
                line = bufferedReader.readLine();
                System.out.println(line);
                data = data + line;
            }
            JO = new JSONObject(data);




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            try{
                commAllProjectsActivity.getDataProjects(this.JO);
            } catch (NullPointerException e){
                visitorCreationActivity.getDataProjects(this.JO);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}


