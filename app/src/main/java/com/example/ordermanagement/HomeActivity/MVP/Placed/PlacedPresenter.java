package com.example.ordermanagement.HomeActivity.MVP.Placed;

import android.content.Context;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;
import com.example.ordermanagement.Utilities.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlacedPresenter implements PlacedContract.presenter
{
    PlacedContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();
    public PlacedPresenter(PlacedContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getOrders(Context context) {
        SharedPref sharedPref=new SharedPref(context);
        clientAPI.getOrderList(sharedPref.getCompany(),sharedPref.getFromDate(),sharedPref.getToDate(),"Placed").enqueue(new Callback<OrderListResponse>() {
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
