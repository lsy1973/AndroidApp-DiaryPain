package com.example.paindiarysecond;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequest_Interface {
//    @GET("api?version=v61&appid=93668184&appsecret=P4TUik0F&cityid=101190401")
//    Call<Reception> getCall();
    @GET("api?version=v61")
    Call<Reception> getCall(@Query("cityid") String cityid,
                            @Query("appid") String appid,
                            @Query("appsecret") String appsecret
    );
}
