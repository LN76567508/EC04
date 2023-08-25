package com.jayo.ec04.data.retrofit;

import com.jayo.ec04.data.response.ShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesInterface {

    @GET("f33d25df-1a48-4ba4-8106-82fa5df0b9f2")
    Call<ShowResponse> getShows();
}
