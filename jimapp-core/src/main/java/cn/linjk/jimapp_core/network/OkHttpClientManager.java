package cn.linjk.jimapp_core.network;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by LinJK on 7/1/16.
 *
 * @version 1.0
 */
public class OkHttpClientManager {

    public static OkHttpClientManager getInstance() {
        if (null == mInstance) {
            synchronized (OkHttpClientManager.class) {
                if (null == mInstance) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }

        return mInstance;
    }

    public static OkHttpClient getClient() {
        return getInstance().client();
    }

    public OkHttpClient client() {
        return mOkHttpClient;
    }

    //===============================================================
    //region Delegates

    //endregion
    //===============================================================
    private static OkHttpClientManager mInstance;

    private OkHttpClient mOkHttpClient;
    private Handler      mDelivery;
    private Gson         mGson;

    private OkHttpClientManager() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

        mDelivery     = new Handler(Looper.getMainLooper());

        mGson         = new Gson();

        /**Just for test , haven't implement it*/
        mOkHttpClient.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }
}
