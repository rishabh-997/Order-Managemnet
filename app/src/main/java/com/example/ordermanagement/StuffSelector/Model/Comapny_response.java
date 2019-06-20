package com.example.ordermanagement.StuffSelector.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comapny_response
{
    @SerializedName("success")
    @Expose
    Boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("unit_list")
    @Expose
    List<Company_List> comapany_list;

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Company_List> getComapany_list() {
        return comapany_list;
    }
}
