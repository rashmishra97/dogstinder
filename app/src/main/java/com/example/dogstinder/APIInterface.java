package com.example.dogstinder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIInterface {

    @GET("breeds/image/random")
    Call<DataModel> get_image();

}
