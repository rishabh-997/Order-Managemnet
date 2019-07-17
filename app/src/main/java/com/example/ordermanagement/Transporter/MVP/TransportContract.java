package com.example.ordermanagement.Transporter.MVP;

import com.example.ordermanagement.Transporter.Model.DispatchResponse;
import com.example.ordermanagement.Transporter.Model.TransportResponse;

public class TransportContract {
    interface view{
        void showToast(String message);
        void showTransporterData(TransportResponse body);

        void showResult(DispatchResponse body);

        void showDispatchStatus();
    }
    interface presenter{
        void getTransporterData();

        void dispatch(String orderid, String mobile);

        void escalate(String orderid, String client);
    }
}
