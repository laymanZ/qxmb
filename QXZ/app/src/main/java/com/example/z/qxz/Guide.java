package com.example.z.qxz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class Guide  extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qx_guide);
        getSupportActionBar().hide();
    }
}
