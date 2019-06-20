package com.example.ordermanagement.HomeActivity.MVP.Dispatched;

public class DispatchedPresenter implements DispatchedContract.presenter
{
    DispatchedContract.view mvpview;

    public DispatchedPresenter(DispatchedContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
