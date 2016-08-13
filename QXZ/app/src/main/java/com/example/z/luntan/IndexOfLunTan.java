package com.example.z.luntan;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.z.qxz.R;

import java.util.ArrayList;
import java.util.List;

import ren.solid.skinloader.base.SkinBaseActivity;
import ren.solid.skinloader.load.SkinManager;
import ren.solid.skinloader.statusbar.StatusBarBackground;


public class IndexOfLunTan extends SkinBaseActivity
{
    TabLayout mTabLayout;
    ViewPager mViewPager;
    List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luntan_index);



        initView();
        dynamicAddSkinEnableView(mTabLayout, "background", R.color.colorPrimary);

       /* findViewById(android.R.id.home).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });*/
    }

    private void initView()
    {

        list = new ArrayList<Fragment>();
        mTabLayout = (TabLayout)findViewById(R.id.id_tab);
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);
        Fragment1  fragment1 = new Fragment1();
        Fragment2  fragment2 = new Fragment2();
        Fragment3  fragment3 = new Fragment3();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);


        AdapterTest adapterTest = new AdapterTest(getSupportFragmentManager(),list);
        mViewPager.setAdapter(adapterTest);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
