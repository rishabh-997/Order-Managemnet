package com.example.ordermanagement.HomeActivity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryDetailResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("detail_list")
    @Expose
    List<HistoryDetailList> historyDetailList;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<HistoryDetailList> getHistoryDetailList() {
        return historyDetailList;
    }
}
