package com.example.ordermanagement.HomeActivity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransportResponse
{
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean result;

    @SerializedName("user_list")
    @Expose
    private
    List<TransportList> list;

    public TransportResponse(String message, Boolean result, List<TransportList> list) {
        this.message = message;
        this.result = result;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getResult() {
        return result;
    }

    public List<TransportList> getList() {
        return list;
    }
}
