package com.example.ordermanagement.StuffSelector.Model;

import com.google.gson.annotations.SerializedName;

public class Company_List
{
    @SerializedName("Company")
    String comapanyfull;

    @SerializedName("ShorForm")
    String companyshort;

    public String getComapanyfull() {
        return comapanyfull;
    }

    public String getCompanyshort() {
        return companyshort;
    }
}
