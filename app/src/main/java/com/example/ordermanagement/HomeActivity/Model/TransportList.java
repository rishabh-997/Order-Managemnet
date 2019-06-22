package com.example.ordermanagement.HomeActivity.Model;

public class TransportList
{
    private String Name, Mobile;

    public TransportList(String name, String mobile) {
        Name = name;
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public String getMobile() {
        return Mobile;
    }
}
