package com.jayo.ec04.data.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHelper {

    public static Retrofit instance;
    public static MoviesInterface service;

    public RetrofitHelper() {
    }

    public static  Retrofit getInstance(){
        if(instance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    //https://run.mocky.io/v3/4faf6808-0f14-4fd6-a69e-2230d87d6d83
                    .baseUrl("https://run.mocky.io/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLoggingBuilder().build())
                    .build();
            instance = retrofit;

        }return instance;
    }
    public static OkHttpClient.Builder getLoggingBuilder(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        return builder;
    }

    public static MoviesInterface getService(){
        if(service == null){
            service = getInstance().create(MoviesInterface.class);
        }
        return service;
    }
}
