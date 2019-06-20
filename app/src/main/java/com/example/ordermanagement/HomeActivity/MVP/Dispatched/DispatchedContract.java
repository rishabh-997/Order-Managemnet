package com.example.ordermanagement.HomeActivity.MVP.Dispatched;

import android.content.Context;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;

public class DispatchedContract
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
