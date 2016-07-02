package cn.linjk.jimapp_core.network;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by LinJK on 7/1/16.
 */
public abstract class OkHttpApi {

    /**
     * Instantiates a new Ok http api.
     */
    public OkHttpApi() {}

    /**
     * request path is like: http://xxxxxx.xx:xx/(param:path)
     *
     * @param path the path
     */
    public void getPath(String path) {
        String protocolString = (currentProtocol==Protocol.HTTP ? "http://" : "https://");

        Request request = new Request.Builder().url(protocolString + currentBaseUrl + "/" + path).build();

        doTheCall(request);
    }

    /**
     * The interface On request call back.
     */
    public interface OnRequestCallBack {
        /**
         * On before.
         *
         * @param requestId the request id
         */
        void onBefore(int requestId);

        /**
         * On failure.
         *
         * @param requestId the request id
         */
        void onFailure(int requestId);

        /**
         * On response.
         *
         * @param requestId the request id
         * @param result    the result
         */
        void onResponse(int requestId, String result);
    }

    private OnRequestCallBack onRequestCallBack;

    /**
     * Add listener.
     *
     * @param callBack the call back
     */
    public void addListener(OnRequestCallBack callBack){
        this.onRequestCallBack = callBack;
    }

    /**
     * Sets request id.
     *
     * @param requestId the request id
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    //////////////////////////////////////////////////////////////////////
    private void doTheCall(final Request request) {
        OkHttpClient client = OkHttpClientManager.getClient();
        client.setConnectTimeout(15, TimeUnit.SECONDS);
        client.setWriteTimeout(7, TimeUnit.SECONDS);
        client.setReadTimeout(7, TimeUnit.SECONDS);

        onRequestCallBack.onBefore(requestId);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();

                onRequestCallBack.onFailure(requestId);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mResponse = response;

                onRequestCallBack.onResponse(requestId, response.body().string());
            }
        });
    }

    /**
     * The enum Protocol.
     */
//////////////////////////////////////////////////////////////////////
    public enum Protocol {
        /**
         * Http protocol.
         */
        HTTP, /**
         * Https protocol.
         */
        HTTPS
    }

    /**
     * The constant JSON.
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * The Current base url.
     */
    protected String   currentBaseUrl;
    /**
     * The Current protocol.
     */
    protected Protocol currentProtocol;

    private final String SESSION_ID    = "connectsid";
    private final String Authorization = "Authorization";
    private final String USER_AGENT    = "User-Agent";

    private String sessionId           = "";
    private String authorization       = "";
    private String userAgent           = "";

    private Response   mResponse;

    private int        requestId;
}
