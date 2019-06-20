package com.example.ordermanagement.StuffSelector.MVP;

import com.example.ordermanagement.StuffSelector.Model.Comapny_response;

public class StuffSelectorContract
{
    interface view
    {

        void showtaost(String message);

        void showCompanies(Comapny_response body);
    }
    interface preseter
    {

        void getCompany();
    }
}
