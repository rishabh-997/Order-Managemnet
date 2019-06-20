package com.example.ordermanagement.HomeActivity.MVP.AdapterForOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ordermanagement.HomeActivity.Model.ClientList;
import com.example.ordermanagement.R;

import java.util.List;

import butterknife.BindView;

public class AdapterForOrder extends RecyclerView.Adapter<AdapterForOrder.ViewHolder>
{
    List<ClientList> list;
    Context context;
    String tabname;

    public AdapterForOrder(List<ClientList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterForOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.card_order_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForOrder.ViewHolder viewHolder, int i)
    {
        ClientList history=list.get(i);
        viewHolder.id.setText(history.getOrderId());
        viewHolder.date.setText(history.getDateOfPurchase());
        viewHolder.cost.setText("Payable excluding GST: "+history.getTotalCost());
        viewHolder.totalcost.setText("Payable including GST : "+history.getTotal_Cost_WTax());
        viewHolder.paymentterms.setText("PAYMENT TERMS : "+history.getPaymentTerms());
        viewHolder.paymentstatus.setText("PAYMENT STATUS : "+history.getPaymentStatus());
        viewHolder.orderstatus.setText("ORDER STATUS : "+history.getOrder_Status());

        if(tabname.equals("Prepared"))
            viewHolder.escalate.setText("Move It To Dispatched");
        else if(tabname.equals("Placed"))
            viewHolder.escalate.setText("Move It To Prepared");
        else if(tabname.equals("Dispatched"))
            viewHolder.escalate.setText("Move It To Delivered");
        else if(tabname.equals("Delivered"))
            viewHolder.escalate.setText("Congratulations");
        else if(tabname.equals("Cancelled"))
            viewHolder.escalate.setText("The Order Was Cancelled");
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public void setname(String msg) {
        tabname=msg;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView id,date,cost,totalcost,paymentstatus,paymentterms,orderstatus;
        Button escalate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.history_orderid);
            date=itemView.findViewById(R.id.history_date);
            cost=itemView.findViewById(R.id.history_cost);
            totalcost=itemView.findViewById(R.id.history_totalcost);
            paymentstatus=itemView.findViewById(R.id.history_paymentstatus);
            paymentterms=itemView.findViewById(R.id.history_paymentterms);
            orderstatus=itemView.findViewById(R.id.history_orderstatus);
            escalate=itemView.findViewById(R.id.escalate);
        }
    }


}
