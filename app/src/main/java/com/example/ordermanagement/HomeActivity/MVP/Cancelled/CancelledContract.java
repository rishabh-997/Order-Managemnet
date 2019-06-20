package com.example.ordermanagement.HomeActivity.MVP.Cancelled;

import android.content.Context;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;

public class CancelledContract
{
    interface view
    {

        void showOrder(OrderListResponse body);

        void showToast(String message);
    }
    interface presenter
    {

        void getOrders(Context context);
    }
}
