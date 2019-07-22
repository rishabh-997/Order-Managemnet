package com.example.ordermanagement.Transporter.MVP;

import com.example.ordermanagement.HomeActivity.Model.EscalateResponse;
import com.example.ordermanagement.Transporter.Model.DispatchResponse;
import com.example.ordermanagement.Transporter.Model.TransportResponse;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportPresenter implements TransportContract.presenter
{
    TransportContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public TransportPresenter(TransportContract.view mvpview) {
        this.mvpview = mvpview;
    }


    @Override
    public void getTransporterData() {
        clientAPI.getTransportList("2").enqueue(new Callback<TransportResponse>() {
            @Override
            public void onResponse(Call<TransportResponse> call, Response<TransportResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showTransporterData(response.body());
                    }
                    else
                    {
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<TransportResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }

    @Override
    public void dispatch(String orderid, String mobile) {
        clientAPI.dispatch(orderid, mobile).enqueue(new Callback<DispatchResponse>() {
            @Override
            public void onResponse(Call<DispatchResponse> call, Response<DispatchResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showResult(response.body());
                    }
                    else
                    {
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<DispatchResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void escalate(String orderid, String client) {
        clientAPI.escalateOrder(orderid,client).enqueue(new Callback<EscalateResponse>() {
            @Override
            public void onResponse(Call<EscalateResponse> call, Response<EscalateResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                        mvpview.showDispatchStatus();
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<EscalateResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }

    @Override
    public void search(String toString, String s) {
        clientAPI.search(s,toString).enqueue(new Callback<TransportResponse>() {
            @Override
            public void onResponse(Call<TransportResponse> call, Response<TransportResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                        mvpview.showTransporterData(response.body());
                    else
                        mvpview.showToast(response.body().getMessage());
                }
                else
                    mvpview.showToast(response.message());
            }

            @Override
            public void onFailure(Call<TransportResponse> call, Throwable t) {

            }
        });
    }
}
