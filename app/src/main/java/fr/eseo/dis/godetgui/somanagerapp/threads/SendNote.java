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
import fr.eseo.dis.godetgui.somanagerapp.LogActivity;
import fr.eseo.dis.godetgui.somanagerapp.NoteProjectActivity;

public class SendNote extends AsyncTask<Void, Void, Void> {

    NoteProjectActivity noteProjectActivity;
    String user;
    String token;
    String idProject;
    String note;
    String data = "";
    JSONObject JO;
    String idStudent;

    public SendNote(NoteProjectActivity noteProjectActivity, String user, String token, String idProject, String note, String idStudent){
        this.noteProjectActivity = noteProjectActivity;
        this.user = user;
        this.token = token;
        this.note = note;
        this.idProject = idProject;
        this.idStudent = idStudent;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.noteProjectActivity.getApplicationContext());
        try {

            URL url =  new URL("https://192.168.4.248/pfe/webservice.php?q=NEWNT&user="+user+"&proj="+idProject+"&student="+idStudent+"&note="+note+"&token="+token);

            //Créer une connection
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(trustManager.getSSLContext().getSocketFactory());
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

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
    protected void onPostExecute(Void aVoid){
        noteProjectActivity.getData();

    }
}
