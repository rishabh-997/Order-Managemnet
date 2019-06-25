package com.example.ordermanagement.StuffSelector.MVP;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordermanagement.HomeActivity.MVP.HomeActivity;
import com.example.ordermanagement.Login.MVP.LogInActivity;
import com.example.ordermanagement.R;
import com.example.ordermanagement.SplashActivity;
import com.example.ordermanagement.StuffSelector.Model.Comapny_response;
import com.example.ordermanagement.StuffSelector.Model.Company_List;
import com.example.ordermanagement.Utilities.SharedPref;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StuffSelectorActivity extends AppCompatActivity implements StuffSelectorContract.view,DatePickerDialog.OnDateSetListener
{
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.selector_from)
    TextView selector_from;
    @BindView(R.id.selector_to)
    TextView selector_to;
    @BindView(R.id.selector_from_image)
    ImageView selector_from_image;
    @BindView(R.id.selector_to_image)
    ImageView selector_to_image;
    @BindView(R.id.selector_proceed)
    Button proceed;
    @BindView(R.id.selector_bar)
    ProgressBar progressBar;


    final static String[] months={"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"};
    List<Company_List> comapany_list=new ArrayList<>();
    String CompanyNameFull="",CompanyNameShort="";
    StuffSelectorContract.preseter preseter;
    DatePickerDialog datePickerDialog;
    int id=0;
    SharedPref sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        preseter=new StuffSelectorPresenter(this);
        ButterKnife.bind(this);

        sharedPref=new SharedPref(this);
        preseter.getCompany();

        //current date
        Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
        String current=calendar.get(Calendar.DAY_OF_MONTH) + " " + months[(calendar.get(Calendar.MONTH))]
                + "," + calendar.get(Calendar.YEAR);

        sharedPref.setToDate(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
        selector_to.setText(current);


        //previous date
        calendar.add(Calendar.DAY_OF_YEAR,-7);
        String previous=calendar.get(Calendar.DAY_OF_MONTH) + " " + months[(calendar.get(Calendar.MONTH))]
                + "," + calendar.get(Calendar.YEAR);
        sharedPref.setFromDate(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
        selector_from.setText(previous);


        //date selector
        datePickerDialog = new DatePickerDialog(this,this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));

        selector_from_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=0;
                datePickerDialog.show();
            }
        });

        selector_to_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=1;
                datePickerDialog.show();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceednext();
            }
        });
    }

    @Override
    public void showtaost(String message) {

    }

    @Override
    public void showCompanies(Comapny_response body) {
        comapany_list=body.getComapany_list();

        String[] companylistfinal=new String[comapany_list.size()+1];
        companylistfinal[0]="Default";
        for(int i=0;i<comapany_list.size();i++){
            companylistfinal[i+1]=comapany_list.get(i).getComapanyfull();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(StuffSelectorActivity.this, android.R.layout.simple_list_item_1, companylistfinal);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                CompanyNameFull=spinner.getSelectedItem().toString();
                setCategory(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setCategory(String Company)
    {
        for(int i=0;i<comapany_list.size();i++)
        {
            if(Company.equals(comapany_list.get(i).getComapanyfull())) {
                CompanyNameShort = comapany_list.get(i).getCompanyshort();
                break;
            }
        }

        SharedPref sharedPref=new SharedPref(this);
        sharedPref.setCompany(CompanyNameShort);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String message=dayOfMonth+" "+months[month]+","+year;
        if(id==0) {
            sharedPref.setFromDate(dayOfMonth+"/"+(month+1)+"/"+year);
            selector_from.setText(message);
        }
        if(id==1) {
            sharedPref.setToDate(dayOfMonth+"/"+(month+1)+"/"+year);
            selector_to.setText(message);
        }
    }


    private void proceednext()
    {
        String from=sharedPref.getFromDate();
        String to=sharedPref.getToDate();
        String company=sharedPref.getCompany();

        if(selector_from.getText().toString().isEmpty())
            Toast.makeText(this, "Please Select a Date", Toast.LENGTH_SHORT).show();
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            },1100);
            startActivity(new Intent(this,HomeActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPref sharedPref=new SharedPref(this);
        int id=item.getItemId();
        if(id==R.id.nav_logout)
        {
            sharedPref.setAccessToken("");
            sharedPref.setKeyAccessLevel("");
            finish();
            startActivity(new Intent(this, LogInActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
