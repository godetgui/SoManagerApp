package fr.eseo.dis.godetgui.somanagerapp.threads;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
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
import fr.eseo.dis.godetgui.somanagerapp.DetailsProjectsJMActivity;

public class
FetchJuryProjects extends AsyncTask<Void, Void, Void> {

    String data = "";
    JSONObject JO;
    String user;
    String token;
    String juryId;
    String projectId;

    DetailsProjectsJMActivity detailsProjectsJMActivity;


    public FetchJuryProjects(DetailsProjectsJMActivity detailsProjectsJMActivity, String user, String token, String juryId, String projectId){
        this.detailsProjectsJMActivity = detailsProjectsJMActivity;
        this.user = user;
        this.token = token;
        this.juryId = juryId;
        this.projectId= projectId;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.detailsProjectsJMActivity.getApplicationContext());
        try {


            URL url =  new URL("https://192.168.4.248/pfe/webservice.php?q=JYINF&user="+user+"&jury="+juryId+"&token="+token);
            //Créer une connection
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(trustManager.getSSLContext().getSocketFactory());
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //Lecture de la réponse et stockage dans un JSONObject
            String line="";
            while(line != null){
                line = bufferedReader.readLine();
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

            this.detailsProjectsJMActivity.getDataProjectsDetails(this.JO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}


