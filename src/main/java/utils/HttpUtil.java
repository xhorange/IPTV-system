package utils;

import com.google.gson.Gson;
import model.ChannelResponse;
import model.TvChannelModel;
import model.TvShowResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static utils.HttpUrl.json;

//工具类 发起网络连接
public class HttpUtil {
    private static HttpUtil httpUtil;
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    public static HttpUtil getInstance() {
        synchronized (HttpUtil.class) {
            if (httpUtil == null) {
                httpUtil = new HttpUtil();
            }
            return httpUtil;
        }
    }

    public String getInfo(String url) {
        if (url == null || "".equals(url)) {
            // log.error("url为null!");
            return "";
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody body = response.body();
                if (body != null) {
                    return body.string();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }

    public ChannelResponse getChannelInfo(String url) {
        if (url == null || "".equals(url)) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody body = response.body();
                if (body != null) {
                    Gson gson = new Gson();
                    ChannelResponse channelResponse = gson.fromJson(body.string(), ChannelResponse.class);
                    return channelResponse;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }
    public TvShowResponse getShowInfo(String url) {
        if (url == null || "".equals(url)) {
            return null;
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                ResponseBody body = response.body();
                if (body != null) {
                    Gson gson = new Gson();
                    TvShowResponse tvShowResponse = gson.fromJson(body.string(), TvShowResponse.class);
                    return tvShowResponse;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }
}
