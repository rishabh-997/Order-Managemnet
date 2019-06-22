package com.example.ordermanagement.HomeActivity.MVP.OrderDetails;

import com.example.ordermanagement.HomeActivity.Model.HistoryDetailResponse;

public class OrderDetailContract
{
    interface view
    {

        void showDetails(HistoryDetailResponse body, String orderid);

        void showToast(String message);

        void showSuccess();
    }
    interface presenter
    {

        void getDetails(String orderid);

        void updateOrder(String id, String orderid, String cost, String size, String unit);
    }
}
