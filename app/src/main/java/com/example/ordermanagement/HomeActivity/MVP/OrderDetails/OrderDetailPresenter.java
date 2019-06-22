package com.example.ordermanagement.HomeActivity.MVP.OrderDetails;

import com.example.ordermanagement.HomeActivity.Model.EscalateResponse;
import com.example.ordermanagement.HomeActivity.Model.HistoryDetailResponse;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailPresenter implements OrderDetailContract.presenter
{
    OrderDetailContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public OrderDetailPresenter(OrderDetailContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getDetails(final String orderid) {
        clientAPI.getOrderDetailsHistory(orderid).enqueue(new Callback<HistoryDetailResponse>() {
            @Override
            public void onResponse(Call<HistoryDetailResponse> call, Response<HistoryDetailResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showDetails(response.body(),orderid);
                    }
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<HistoryDetailResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }

    @Override
    public void updateOrder(String id, String orderid, String cost, String size, String unit) {
        clientAPI.updateProduct(orderid,id,size,unit,cost,"Client").enqueue(new Callback<EscalateResponse>() {
            @Override
            public void onResponse(Call<EscalateResponse> call, Response<EscalateResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showSuccess();
                    }
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<EscalateResponse> call, Throwable t) {

            }
        });
    }
}
