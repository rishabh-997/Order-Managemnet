package com.example.ordermanagement.HomeActivity.MVP.Placed;

public class PlacedPresenter implements PlacedContract.presenter
{
    PlacedContract.view mvpview;

    public PlacedPresenter(PlacedContract.view mvpview) {
        this.mvpview = mvpview;
    }
}
