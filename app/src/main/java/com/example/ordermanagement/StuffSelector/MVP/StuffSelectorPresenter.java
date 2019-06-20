package com.example.ordermanagement.StuffSelector.MVP;

import com.example.ordermanagement.StuffSelector.Model.Comapny_response;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StuffSelectorPresenter implements StuffSelectorContract.preseter
{
    StuffSelectorContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public StuffSelectorPresenter(StuffSelectorContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getCompany()
    {
        clientAPI.getCompany("9935685103").enqueue(new Callback<Comapny_response>() {
            @Override
            public void onResponse(Call<Comapny_response> call, Response<Comapny_response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        mvpview.showCompanies(response.body());
                    }
                    else
                        mvpview.showtaost(response.message());
                }
            }
            @Override
            public void onFailure(Call<Comapny_response> call, Throwable t) {

            }
        });
    }
}
