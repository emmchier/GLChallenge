package com.example.logicmarketchallenge.core.service;
import com.example.logicmarketchallenge.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit;
    private static String baseURL = "http://private-f0eea-mobilegllatam.apiary-mock.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpclient = new OkHttpClient.Builder();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

            retrofit = builder.client(httpclient.build()).build();
        }
        return retrofit;
    }
}
