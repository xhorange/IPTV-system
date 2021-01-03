package utils;

import com.google.gson.Gson;
import model.ChannelResponse;
import model.TvChannelModel;
import model.TvShowResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//工具类 发起网络连接
public class HttpUtil {
    private static HttpUtil httpUtil;
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
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
                String data = response.body().string();
                if (!data.equals("")) {
                    Gson gson = new Gson();
                    return gson.fromJson(data, ChannelResponse.class);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "网络连接错误!\n错误地址" + url + "\n" + "错误原因：" + e);
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
                    return gson.fromJson(body.string(), TvShowResponse.class);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
        }
        return null;
    }
}
