package com.example.z.skin;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;

import com.example.z.qxz.R;

public class SkinActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skin_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.skin_toolbar);
        toolbar.setTitle("主题换肤");

    }
}
