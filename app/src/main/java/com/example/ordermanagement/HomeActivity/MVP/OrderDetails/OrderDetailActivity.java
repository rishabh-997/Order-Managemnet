package com.example.ordermanagement.HomeActivity.MVP.OrderDetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordermanagement.HomeActivity.MVP.HomeActivity;
import com.example.ordermanagement.HomeActivity.Model.HistoryDetailList;
import com.example.ordermanagement.HomeActivity.Model.HistoryDetailResponse;
import com.example.ordermanagement.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailContract.view,OrderDetailAdapter.onNoteClickListener
{
    OrderDetailContract.presenter presenter;
    OrderDetailAdapter adapter;
    List<HistoryDetailList> list=new ArrayList<>();
    @BindView(R.id.orderDetail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.orderDetail_id)
    TextView id;
    @BindView(R.id.orderdetails_comment)
    TextView ordercomment;
    String orderid,comment;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        presenter=new OrderDetailPresenter(this);
        ButterKnife.bind(this);

        progressDialog=new ProgressDialog(this);
        Intent intent=getIntent();
        orderid=intent.getStringExtra("orderid");
        comment=intent.getStringExtra("comment");
        id.setText(orderid);
        ordercomment.setText(comment);
        if(comment.trim().isEmpty())
            ordercomment.setText("No Comment Found");
        presenter.getDetails(orderid);
    }

    @Override
    public void showDetails(HistoryDetailResponse body, String orderid)
    {
        list.clear();
        list=body.getHistoryDetailList();
        adapter=new OrderDetailAdapter(this,list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showToast(String message) {
        progressDialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        progressDialog.dismiss();
        Toast.makeText(this, "Updtaed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateClick(int position, String cost, String size, String unit) {

        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

        presenter.updateOrder(list.get(position).getId(),orderid,cost,size,unit);
    }

    @Override
    public void onDeleteClick(int position) {
        Toast.makeText(this, "Clicked on delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }
}
