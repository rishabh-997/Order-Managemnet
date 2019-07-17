package com.example.ordermanagement.Transporter.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordermanagement.R;
import com.example.ordermanagement.Transporter.Model.DispatchResponse;
import com.example.ordermanagement.Transporter.Model.TransportList;
import com.example.ordermanagement.Transporter.Model.TransportResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransportActivity extends AppCompatActivity implements TransportContract.view
{
    TransportContract.presenter presenter;
    List<TransportList> list=new ArrayList<>();

    @BindView(R.id.trans_bar)
    ProgressBar progressBar;
    @BindView(R.id.trans_spinner)
    Spinner spinner;
    @BindView(R.id.trans_name)
    TextView trans_name;
    @BindView(R.id.trans_order)
    TextView trans_orderid;
    @BindView(R.id.trans_comment)
    TextView trans_comment;
    @BindView(R.id.trans_submit)
    Button submit;

    String name="",mobile="",orderid="",comment="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        ButterKnife.bind(this);
        presenter=new TransportPresenter(this);
        submit.setClickable(false);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height=dm.heightPixels;
        int width=dm.widthPixels;

        getWindow().setLayout((int)(width*0.7),(int)(height*0.65));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=20;

        getWindow().setAttributes(params);

        Intent intent=getIntent();
        orderid=intent.getStringExtra("orderid");
        comment=intent.getStringExtra("comment");
        trans_orderid.setText(orderid);
        if(comment.isEmpty())
            trans_comment.setText("No Additional Info Provided");
        else
            trans_comment.setText(comment);

        progressBar.setVisibility(View.VISIBLE);
        presenter.getTransporterData();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatch();
            }
        });
    }

    private void dispatch()
    {
        if(mobile.isEmpty())
        {
            Toast.makeText(this, "Select Contact", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            presenter.dispatch(orderid,mobile);
        }
    }

    @Override
    public void showResult(DispatchResponse body)
    {
        presenter.escalate(orderid,"Client");
    }

    @Override
    public void showDispatchStatus() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Dispatched Successfully", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showToast(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTransporterData(TransportResponse body)
    {
        submit.setClickable(true);
        progressBar.setVisibility(View.GONE);
        list.clear();
        list=body.getList();

        final String[] contact = new String[list.size()];
        for(int i=0;i<list.size();i++)
        {
            contact[i]=list.get(i).getMobile();
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contact);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mobile=spinner.getSelectedItem().toString();
                name=list.get(position).getName();
                trans_name.setText(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}