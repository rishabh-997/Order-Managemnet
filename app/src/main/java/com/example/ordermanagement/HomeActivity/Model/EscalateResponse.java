package com.example.ordermanagement.HomeActivity.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EscalateResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
