package com.example.ordermanagement.HomeActivity.MVP.Prepared;

import android.content.Context;

import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;

public class PreparedContract
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
