package com.example.ordermanagement.HomeActivity.Model;

import com.example.ordermanagement.StuffSelector.Model.Company_List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderListResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("client_list")
    @Expose
    List<ClientList> client_list;
    @SerializedName("supplier_list")
    @Expose
    List<SupplierList> supplier_list;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<ClientList> getClient_list() {
        return client_list;
    }

    public List<SupplierList> getSupplier_list() {
        return supplier_list;
    }
}
