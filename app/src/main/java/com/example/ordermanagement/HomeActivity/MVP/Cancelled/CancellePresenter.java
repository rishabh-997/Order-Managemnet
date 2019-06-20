package com.example.ordermanagement.HomeActivity.MVP.Cancelled;

public class CancellePresenter implements CancelledContract.presenter
{
    CancelledContract.view mvpview;

    public CancellePresenter(CancelledContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
