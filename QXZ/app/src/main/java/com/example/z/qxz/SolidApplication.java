package com.example.z.qxz;


import ren.solid.skinloader.base.SkinBaseApplication;


public class SolidApplication extends SkinBaseApplication
{
    private static SolidApplication mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;

    }
    public static SolidApplication getInstance()
    {
        return mInstance;
    }
}
