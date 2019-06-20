package com.example.ordermanagement.Login.MVP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ordermanagement.HomeActivity.MVP.HomeActivity;
import com.example.ordermanagement.Login.Model.LogInResponse;
import com.example.ordermanagement.R;
import com.example.ordermanagement.StuffSelector.MVP.StuffSelectorActivity;
import com.example.ordermanagement.Utilities.MyApplication;
import com.example.ordermanagement.Utilities.SharedPref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity implements LogInContract.view
{
    LogInContract.presenter presenter;
    @BindView(R.id.login_login)
    Button login;
    @BindView(R.id.login_email)
    EditText mobile;
    @BindView(R.id.login_password)
    EditText password;
    @BindView(R.id.loginbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter=new LogInPresenter(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(mobile.getText().length()!=10)
                {
                    mobile.setError("Invalid Mobile Number");
                    mobile.requestFocus();
                }
                if(password.getText().toString().isEmpty())
                {
                    password.setError("Enter Password");
                    password.requestFocus();
                }
                if((mobile.getText().length()==10)&&(!password.getText().toString().isEmpty())) {
                    progressBar.setVisibility(View.VISIBLE);
                    presenter.requestlogin(mobile.getText().toString(), password.getText().toString(), MyApplication.getFcm());
                }
            }
        });


    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void enterApp(LogInResponse body) {
        SharedPref sharedPref=new SharedPref(this);
        sharedPref.setAccessToken(body.getAccess_token());
        sharedPref.setKeyAccessLevel(body.getAccess_level());

        Log.i("Access Token",sharedPref.getAccessToken());
        Log.i("Fcm",MyApplication.getFcm());

        finish();
        startActivity(new Intent(this, StuffSelectorActivity.class));
    }

    @Override
    public void hidebar() {
        progressBar.setVisibility(View.GONE);
    }
}
