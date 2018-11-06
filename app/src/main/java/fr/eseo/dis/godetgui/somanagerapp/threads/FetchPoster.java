package fr.eseo.dis.godetgui.somanagerapp.threads;

import android.os.AsyncTask;

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

public class FetchPoster extends AsyncTask<Void, Void, Void> {

    private DetailsProjectsJMActivity detailsProjectsJMActivity;
    private String user;
    private String token;
    private String idSujet;
    private JSONObject JO;
    private String data = "";


    public FetchPoster (DetailsProjectsJMActivity detailsProjectsJMActivity, String user, String token, String idSujet){
        this.detailsProjectsJMActivity = detailsProjectsJMActivity;
        this.user = user;
        this.token = token;
        this.idSujet = idSujet;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        TrustManager trustManager = new TrustManager();
        trustManager.getCertificate(this.detailsProjectsJMActivity.getApplicationContext());

        try {

            URL url =  new URL("https://192.168.4.248/pfe/webservice.php?q=POSTR&user="+user+"&proj="+idSujet+"&style=FLB64&token="+token);

            //Créer une connection
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(trustManager.getSSLContext().getSocketFactory());
            InputStream inputStream = httpsURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            System.out.println("IMAGE: "+ bufferedReader);

            //byte[] imgBytesData = android.util.Base64.decode(bufferedReader);

           /* //Lecture de la réponse et stockage dans un JSONObject
            String line="";
            while(line != null){
                line = bufferedReader.readLine();
                System.out.println(line);
                data = data + line;
            }
            JO = new JSONObject(data);*/




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {

    }



}
