package com.example.ordermanagement.Login.MVP;
import com.example.ordermanagement.Login.Model.LogInResponse;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInPresenter implements LogInContract.presenter
{
    LogInContract.view mvpview;
    ClientAPI clientAPI= Utils.getClientAPI();

    public LogInPresenter(LogInContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void requestlogin(String mob, String pass, String fcm) {
        clientAPI.login(mob,pass,fcm).enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("Login Successful"))
                    {
                        mvpview.hidebar();
                        mvpview.enterApp(response.body());
                    }
                    else
                    {
                        mvpview.hidebar();
                        mvpview.showToast(response.body().getMessage());
                    }
                }
                else{
                    mvpview.showToast("Unsuccessful : "+response.message());
                    mvpview.hidebar();
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                mvpview.showToast("Throwable : "+t.getMessage());
            }
        });
    }
}
