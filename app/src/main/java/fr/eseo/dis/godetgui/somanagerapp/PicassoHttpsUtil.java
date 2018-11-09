package fr.eseo.dis.godetgui.somanagerapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import fr.eseo.dis.godetgui.somanagerapp.Certificates.CertificatesPoster.SSLUtil;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * @author Tristan LE GACQUE
 * Created 15/10/2018
 */
public class PicassoHttpsUtil {

    private PicassoHttpsUtil() {}

    public static Picasso getPicasso(Context context) {
        return buildPicasso(context);
    }

    private static Picasso buildPicasso(Context context) {
        OkHttpClient client = getOkHttpClient(context);

        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e("PICASSO", exception.toString());
                    }
                }).build();
    }

    private static OkHttpClient getOkHttpClient(Context context) {
        try {
            // J'utilise ma classe SSLUtil pour récupérer les infos necessaires
            // (voir https://192.168.4.15/snippets/53 et https://192.168.4.15/snippets/54)
            Pair<SSLContext, TrustManager> sslTrust = SSLUtil.getSSLAndTrust(context);
            SSLContext sslContext =  sslTrust.first;
            TrustManager trustManager =  sslTrust.second;

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManager);


            // Définir le cache (pour que l'image soit affichée hors ligne)
            int cache = 100 * 1024 * 1024; //100Mb de cache pour être large
            builder.cache(new Cache(context.getCacheDir(), cache));

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

