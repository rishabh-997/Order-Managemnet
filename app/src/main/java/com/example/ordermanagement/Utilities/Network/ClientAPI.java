package com.example.ordermanagement.Utilities.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;
import com.example.ordermanagement.Login.Model.LogInResponse;
import com.example.ordermanagement.StuffSelector.Model.Comapny_response;

public interface ClientAPI {

    @POST("admin_login/")
    @FormUrlEncoded
    Call<LogInResponse> login(
            @Field("mobile")String mobile,
            @Field("password")String password,
            @Field("fcm")String fcm
    );

    @POST("CompanyList/")
    @FormUrlEncoded
    Call<Comapny_response> getCompany(
            @Field("Mob") String mob
    );

    @POST("AdminGetOrderHistory/")
    @FormUrlEncoded
    Call<OrderListResponse> getOrderList(
            @Field("Company") String company,
            @Field("from_date") String fromdate,
            @Field("to_date") String todate,
            @Field("status") String status
    );
}
