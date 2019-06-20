package com.example.ordermanagement.HomeActivity.MVP.Delivered;

import android.content.Context;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;

public class DeliveredContract
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
