package fr.eseo.dis.godetgui.somanagerapp.threads;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

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

    public static void decoder(String base64Image, String pathFile) {
        try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {

    }



}
