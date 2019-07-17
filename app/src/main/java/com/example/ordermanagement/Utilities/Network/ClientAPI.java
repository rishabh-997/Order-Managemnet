package com.example.ordermanagement.Utilities.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import com.example.ordermanagement.HomeActivity.Model.EscalateResponse;
import com.example.ordermanagement.HomeActivity.Model.HistoryDetailResponse;
import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;
import com.example.ordermanagement.Transporter.Model.DispatchResponse;
import com.example.ordermanagement.Transporter.Model.TransportResponse;
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

    @POST("GetOrderDetailsHistory/")
    @FormUrlEncoded
    Call<HistoryDetailResponse> getOrderDetailsHistory(
            @Field("OrderId") String id
    );

    @POST("EscalateOrder/")
    @FormUrlEncoded
    Call<EscalateResponse> escalateOrder(
            @Field("OrderId") String id,
            @Field("Contact_Type") String type
    );

    @POST("AdminUpdateOrder/")
    @FormUrlEncoded
    Call<EscalateResponse> updateProduct(
            @Field("OrderID") String orderid,
            @Field("PID") String productid,
            @Field("Size") String size,
            @Field("Unit") String unit,
            @Field("Cost") String cost,
            @Field("Contact_Type") String type
    );

    @POST("DataBaseRetrival/")
    @FormUrlEncoded
    Call<TransportResponse> getTransportList(
            @Field("RegType") String regtype
    );

    @POST("AdminOrderTransporterData/")
    @FormUrlEncoded
    Call<DispatchResponse> dispatch(
            @Field("OrderID") String id,
            @Field("Mobile") String mobile
    );
}
