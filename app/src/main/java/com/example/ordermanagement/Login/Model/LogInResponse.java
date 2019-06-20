package com.example.ordermanagement.Login.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogInResponse
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("access_token")
    @Expose
    String access_token;
    @SerializedName("Access_Level")
    @Expose
    String access_level;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getAccess_level() {
        return access_level;
    }
}
