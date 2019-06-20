package com.example.ordermanagement.Utilities.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import com.example.ordermanagement.Login.Model.LogInResponse;

public interface ClientAPI {

    @POST("admin_login/")
    @FormUrlEncoded
    Call<LogInResponse> login(
            @Field("mobile")String mobile,
            @Field("password")String password,
            @Field("fcm")String fcm
    );
}
