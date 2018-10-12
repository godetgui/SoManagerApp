package fr.eseo.dis.godetgui.somanagerapp.threads;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import fr.eseo.dis.godetgui.somanagerapp.Certificates.TrustManager;
import fr.eseo.dis.godetgui.somanagerapp.LogActivity;

public class FetchDataLogon extends AsyncTask<Void, Void, Void> {

    String data = "";
    JSONObject JO;
    Context context;
    String user;
    String password;

    LogActivity logActivity = new LogActivity();

    public FetchDataLogon(Context context, String user, String password){
        this.context = context;
        this.user = user;
        this.password = password;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.context);
        try {

            URL url =  new URL("https://192.168.4.248/pfe/webservice.php?q=LOGON&user="+user+"&pass="+password);

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
            logActivity.getDataLogon(this.JO, user);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
