package com.financial.android.utils.httputils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wyy on 2016/4/6.
 */
public class OkHttpUtils {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;


    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }


    private  Response get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = mOkHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    private String getString(String url) throws IOException {
        Response response = get(url);
        return response.body().string();
    }

    //同步请求
    public static Response getAsynReponse(String url) throws IOException {
        return mInstance.get(url);
    }

    public static String getAsynString(String url) throws IOException {
        return mInstance.getString(url);
    }






}
