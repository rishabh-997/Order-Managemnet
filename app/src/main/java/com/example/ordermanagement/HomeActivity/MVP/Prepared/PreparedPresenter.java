package com.example.ordermanagement.HomeActivity.MVP.Prepared;

public class PreparedPresenter implements PreparedContract.presenter
{
    PreparedContract.view mvpview;

    public PreparedPresenter(PreparedContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
