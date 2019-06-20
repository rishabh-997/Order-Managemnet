package com.example.ordermanagement.HomeActivity.Model;

import com.google.gson.annotations.SerializedName;

public class ClientList
{
    @SerializedName("OrderId")
    String OrderId;
    @SerializedName("DateOfPurchase")
    String DateOfPurchase;
    @SerializedName("TotalCost")
    String TotalCost;
    @SerializedName("Total_Cost_WTax")
    String Total_Cost_WTax;
    @SerializedName("PaymentStatus")
    String PaymentStatus;
    @SerializedName("PaymentTerms")
    String PaymentTerms;
    @SerializedName("Order_Status")
    String Order_Status;
    @SerializedName("Comment")
    String comment;

    public String getOrderId() {
        return OrderId;
    }

    public String getDateOfPurchase() {
        return DateOfPurchase;
    }

    public String getTotalCost() {
        return TotalCost;
    }

    public String getTotal_Cost_WTax() {
        return Total_Cost_WTax;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public String getPaymentTerms() {
        return PaymentTerms;
    }

    public String getOrder_Status() {
        return Order_Status;
    }

    public String getComment() {
        return comment;
    }
}
