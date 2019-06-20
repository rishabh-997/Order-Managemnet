package com.example.ordermanagement.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private static final String KEY_ACCESS_LEVEL = "access_level";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String COMPANY_NAME="company";
    private static final String PREF_NAME = "welcome";
    private static final String FIRST_TIME = "first";
    private static final String FROM_DATE = "from";
    private static final String TO_DATE = "to";


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    public SharedPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, "");
    }
    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getKeyAccessLevel()
    {
        return pref.getString(KEY_ACCESS_LEVEL,"");
    }
    public void setKeyAccessLevel(String accessLevel)
    {
        editor.putString(KEY_ACCESS_LEVEL,accessLevel);
        editor.commit();
    }

    public String getCompany()
    {
        return pref.getString(COMPANY_NAME,null);
    }
    public void setCompany(String company)
    {
        editor.putString(COMPANY_NAME,company);
        editor.commit();
    }

    public String getFromDate()
    {
        return pref.getString(FROM_DATE,null);
    }
    public void setFromDate(String date)
    {
        editor.putString(FROM_DATE,date);
        editor.commit();
    }

    public String getToDate()
    {
        return pref.getString(TO_DATE,null);
    }
    public void setToDate(String date)
    {
        editor.putString(TO_DATE,date);
        editor.commit();
    }

}
