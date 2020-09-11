package com.example.poslovnihodogram.retrofit.api;


import com.example.poslovnihodogram.settings.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitGzip = null;
    public static Retrofit gzipXmlClient = null;
    public static Retrofit xmlClient = null;


    public static Retrofit getJsonClient(){
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setLenient()
                .create();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.timeout, TimeUnit.SECONDS);
        builder.readTimeout(Constants.timeout, TimeUnit.SECONDS);
        builder.addNetworkInterceptor((Interceptor.Chain chain) -> {
            Request req = chain.request();
            Headers.Builder headersBuilder = req.headers().newBuilder();
            Response res = chain.proceed(req.newBuilder().headers(headersBuilder.build()).build());
            return res.newBuilder().build();
        });
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.mainLink)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(builder.build())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getGzipJsonClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.timeout, TimeUnit.SECONDS);
        builder.readTimeout(Constants.timeout, TimeUnit.SECONDS);
        builder.addNetworkInterceptor((Interceptor.Chain chain) -> {
            Request req = chain.request();
            Headers.Builder headersBuilder = req.headers().newBuilder();
            Response res = chain.proceed(req.newBuilder().headers(headersBuilder.build()).build());
            return res.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .header("Content-Type", "application/json")
                    .build();
        });
        if (retrofitGzip == null) {
            retrofitGzip = new Retrofit.Builder()
                    .baseUrl(Constants.mainLink)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(builder.build())
                    .build();
        }
        return retrofitGzip;
    }

    public static Retrofit getGzipXmlClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.addNetworkInterceptor((Interceptor.Chain chain) -> {
            Request req = chain.request();
            Headers.Builder headersBuilder = req.headers().newBuilder();
            Response res = chain.proceed(req.newBuilder().headers(headersBuilder.build()).build());
            return res.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .header("Content-Type", "application/xml")
                    .header("Accept-Encoding", "identity")
                    .build();
        });
        if (gzipXmlClient == null) {
            gzipXmlClient = new Retrofit.Builder()
                    .baseUrl(Constants.mainLink)
                    .client(builder.build())
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                    .build();
        }
        return gzipXmlClient;
    }

    public static Retrofit getXmlClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.timeout, TimeUnit.SECONDS);
        builder.readTimeout(Constants.timeout, TimeUnit.SECONDS);
        builder.addNetworkInterceptor((Interceptor.Chain chain) -> {
            Request req = chain.request();
            Headers.Builder headersBuilder = req.headers().newBuilder();
            Response res = chain.proceed(req.newBuilder().headers(headersBuilder.build()).build());
            return res.newBuilder()
                    .header("Content-Type", "application/xml")
                    .build();
        });
            xmlClient = new Retrofit.Builder()
                    .baseUrl(Constants.mainLink)
                    .client(builder.build())
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                    .build();
        return xmlClient;
    }
}
