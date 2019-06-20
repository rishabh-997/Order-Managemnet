package com.example.ordermanagement.HomeActivity.MVP.Dispatched;

import android.content.Context;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;
import com.example.ordermanagement.Utilities.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispatchedPresenter implements DispatchedContract.presenter
{
    DispatchedContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public DispatchedPresenter(DispatchedContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getOrders(Context context) {
        SharedPref sharedPref=new SharedPref(context);
        clientAPI.getOrderList(sharedPref.getCompany(),sharedPref.getFromDate(),sharedPref.getToDate(),"Dispatched").enqueue(new Callback<OrderListResponse>() {
            @Override
            public void onResponse(Call<OrderListResponse> call, Response<OrderListResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showOrder(response.body());
                    }
                    else
                    {
                        mvpview.showToast(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderListResponse> call, Throwable t) {

            }
        });
    }
}
