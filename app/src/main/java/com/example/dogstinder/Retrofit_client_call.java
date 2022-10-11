package com.example.dogstinder;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_client_call {

    public static com.example.dogstinder.APIInterface getApi() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).build(); //===https://images.dog.ceo/breeds/ovcharka-caucasian/===https://dog.ceo/api/breeds/image/random/
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit r = new Retrofit.Builder().baseUrl("https://dog.ceo/api/").client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return r.create(com.example.dogstinder.APIInterface.class);
    }

}
