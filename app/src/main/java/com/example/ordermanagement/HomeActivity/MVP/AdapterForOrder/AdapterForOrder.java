package com.example.ordermanagement.HomeActivity.MVP.AdapterForOrder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordermanagement.HomeActivity.MVP.OrderDetails.OrderDetailActivity;
import com.example.ordermanagement.HomeActivity.Model.ClientList;
import com.example.ordermanagement.HomeActivity.Model.EscalateResponse;
import com.example.ordermanagement.HomeActivity.Model.OrderListResponse;
import com.example.ordermanagement.R;
import com.example.ordermanagement.Transporter.MVP.TransportActivity;
import com.example.ordermanagement.Utilities.Network.ClientAPI;
import com.example.ordermanagement.Utilities.Network.Utils;
import com.example.ordermanagement.Utilities.SharedPref;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterForOrder extends RecyclerView.Adapter<AdapterForOrder.ViewHolder>
{
    int current_position;
    List<ClientList> list;
    Context context;
    String tabname;
    ClientAPI clientAPI= Utils.getClientAPI();
    ProgressDialog progressDialog;
    RestartFragment restartFragment;

    public AdapterForOrder(List<ClientList> list, Context context) {
        this.list = list;
        this.context = context;
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Updating");
        progressDialog.setCancelable(false);
    }

    @NonNull
    @Override
    public AdapterForOrder.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.card_order_list,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterForOrder.ViewHolder viewHolder, int i)
    {
        current_position=i;
        final ClientList history=list.get(i);
        final String orderid=history.getOrderId();
        viewHolder.id.setText(history.getOrderId());
        viewHolder.date.setText("Order was Placed on "+history.getDateOfPurchase());
        viewHolder.name.setText(history.getName());
        viewHolder.cost.setText(history.getTotalCost());
        viewHolder.totalcost.setText(history.getTotal_Cost_WTax());
        viewHolder.paymentterms.setText(history.getPaymentTerms());
        viewHolder.paymentstatus.setText(history.getPaymentStatus());
        viewHolder.orderstatus.setText(history.getOrder_Status());
        viewHolder.cgst.setText(history.getCgst());
        viewHolder.sgst.setText(history.getSgst());
        String comment=history.getComment();
        if(comment.trim().isEmpty())
            viewHolder.comment.setText("No Comment Found");
        else
            viewHolder.comment.setText(history.getComment());

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

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderDetailActivity.class);
                intent.putExtra("orderid",orderid);
                intent.putExtra("comment",history.getComment());
                context.startActivity(intent);
            }
        });
        viewHolder.escalate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(tabname.equals("Placed") || tabname.equals("Dispatched"))
                {
                    progressDialog.show();
                    clientAPI.escalateOrder(orderid,"Client").enqueue(new Callback<EscalateResponse>() {
                        @Override
                        public void onResponse(Call<EscalateResponse> call, Response<EscalateResponse> response) {
                            shopUpdateStatus(response);
                        }

                        @Override
                        public void onFailure(Call<EscalateResponse> call, Throwable t) {
                            showThrowable(t);
                        }
                    });
                }
                else if(tabname.equals("Prepared"))
                {
                    Intent intent= new Intent(context, TransportActivity.class);
                    intent.putExtra("comment",history.getComment());
                    intent.putExtra("orderid",orderid);
                    context.startActivity(intent);
                }
            }
        });
    }

    private void shopUpdateStatus(Response<EscalateResponse> response)
    {
        if(response.isSuccessful())
        {
            if(response.body().getMessage().equals("successful"))
            {
                updateList();
                progressDialog.dismiss();
                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                progressDialog.dismiss();
                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateList()
    {
        SharedPref sharedPref=new SharedPref(context);
        clientAPI.getOrderList(sharedPref.getCompany(),sharedPref.getFromDate(),sharedPref.getToDate(),tabname).enqueue(new Callback<OrderListResponse>() {
            @Override
            public void onResponse(Call<OrderListResponse> call, Response<OrderListResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getMessage().equals("successful"))
                    {
                        test(response);
                    }
                    else
                    {
                        Toast.makeText(context, "Error Refreshing the List", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OrderListResponse> call, Throwable t) {

            }
        });
    }

    private void test(Response<OrderListResponse> response)
    {
        list.clear();
        list=response.body().getClient_list();
        notifyDataSetChanged();
    }


    private void showThrowable(Throwable t)
    {
        progressDialog.dismiss();
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        TextView id,date,cost,totalcost,cgst,sgst,paymentstatus,paymentterms,orderstatus,name,comment;
        Button escalate;
        RelativeLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.order_status_id);
            date=itemView.findViewById(R.id.order_status_time);
            cost=itemView.findViewById(R.id.order_status_totalcost);
            totalcost=itemView.findViewById(R.id.order_status_totalcosttax);
            paymentstatus=itemView.findViewById(R.id.order_status_payment_status);
            paymentterms=itemView.findViewById(R.id.order_status_payment_terms);
            orderstatus=itemView.findViewById(R.id.order_status_status);
            escalate=itemView.findViewById(R.id.escalate);
            linearLayout=itemView.findViewById(R.id.layout_relative);
            name=itemView.findViewById(R.id.order_status_name);
            cgst=itemView.findViewById(R.id.order_status_cgst);
            sgst=itemView.findViewById(R.id.order_status_sgst);
            comment=itemView.findViewById(R.id.order_status_comment);
        }
    }


    public interface RestartFragment
    {
        void refresh();
    }

}
