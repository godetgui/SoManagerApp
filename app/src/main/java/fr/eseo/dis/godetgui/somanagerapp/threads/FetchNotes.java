package fr.eseo.dis.godetgui.somanagerapp.threads;

import android.os.AsyncTask;

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
import fr.eseo.dis.godetgui.somanagerapp.ConnectionActivity;
import fr.eseo.dis.godetgui.somanagerapp.NoteProjectActivity;

public class FetchNotes extends AsyncTask<Void, Void, Void> {

    String token;
    String data = "";
    JSONObject JO;
    String user;
    String idProject;

    NoteProjectActivity noteprojectactivity;

    public FetchNotes(NoteProjectActivity noteprojectactivity, String idProject, String user, String token){
        this.noteprojectactivity = noteprojectactivity;
        this.idProject = idProject;
        this.token = token;
        this.user = user;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.noteprojectactivity.getApplicationContext());
        try {

            URL url =  new URL("https://192.168.4.248/pfe/webservice.php?q=NOTES&user="+user+"&proj="+idProject+"&token="+token);
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
            noteprojectactivity.getDataNotes(this.JO);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
