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
 *
 * 网络访问抽象基类，具体应用应继承此类根据实际重写get与post等方法
 *
 */
public abstract class OkHttpApi {

    public OkHttpApi() {}

    /**
     *  访问路径格式为: http://xxxxxx.xx:xx/<参数path>
     */
    public void getPath(String path) {
        String protocolString = (currentProtocol==Protocol.HTTP ? "http://" : "https://");

        Request request = new Request.Builder().url(protocolString + currentBaseUrl + "/" + path).build();

        doTheCall(request);
    }

    public interface OnRequestCallBack {
        void onBefore(int requestId);
        void onFailure(int requestId);
        void onResponse(int requestId, String result);
    }

    private OnRequestCallBack onRequestCallBack;

    public void addListener(OnRequestCallBack callBack){
        this.onRequestCallBack = callBack;
    }

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

    //////////////////////////////////////////////////////////////////////
    public enum Protocol {
        HTTP, HTTPS
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected String   currentBaseUrl;
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
