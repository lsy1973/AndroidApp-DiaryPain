package com.example.paindiary;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest_Interface {
    @GET("api?version=v61&appid=93668184&appsecret=BYXa8msX&cityid=101010100")
    Call<Reception> getCall();
}
