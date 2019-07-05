package com.example.ordermanagement.HomeActivity.Model;

import com.google.gson.annotations.SerializedName;

public class HistoryDetailList
{
    @SerializedName("Size")
    String size;
    @SerializedName("Unit")
    String unit;
    @SerializedName("Cost")
    String cost;
    @SerializedName("TotalCost")
    String total_cost;
    @SerializedName("Product_Name")
    String name;
    @SerializedName("Product_id")
    String id;
    @SerializedName("NVM")
    String nvm;
    @SerializedName("Product_Name_New")
    String prod_name_new;

    public String getNvm() {
        return nvm;
    }

    public String getProd_name_new() {
        return prod_name_new;
    }

    public String getName() {
        return name;
    }
    public String getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public String getCost() {
        return cost;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public String getId() {
        return id;
    }
}
