package com.example.ordermanagement.HomeActivity.MVP.Delivered;

public class DeliveredPresenter implements DeliveredContract.presenter
{
    DeliveredContract.view mvpview;

    public DeliveredPresenter(DeliveredContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
