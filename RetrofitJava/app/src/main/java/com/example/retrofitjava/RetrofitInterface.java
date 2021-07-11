package com.example.retrofitjava;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("customsearch/v1?")
    @Headers("User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")
    Call<SearchResponse> customSearch(@Query("key") String API_KEY,
                                      @Query("cx") String SEARCH_ID_cx,
                                      @Query("q") String keyword);
}
