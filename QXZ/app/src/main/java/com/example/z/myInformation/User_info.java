package com.example.z.myInformation;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.baoyz.actionsheet.ActionSheet;
import com.example.z.luntan.AdapterTest;
import com.example.z.luntan.Fragment1;
import com.example.z.luntan.Fragment2;
import com.example.z.luntan.Fragment3;
import com.example.z.qxz.Circle_header;
import com.example.z.qxz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z on 2016/3/30.
 */
public class User_info extends AppCompatActivity
{

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        Toolbar info_toolbar = (Toolbar) findViewById(R.id.info_toolbar);
        setSupportActionBar(info_toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.toolbar_layout);
        collapsingToolbar.setTitleEnabled(false);
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        TabLayout mTabLayout = (TabLayout)findViewById(R.id.info_tab);
        ViewPager mViewPager = (ViewPager)findViewById(R.id.info_viewpager);
        Fragment1  fragment1 = new Fragment1();
        Fragment2  fragment2 = new Fragment2();
        Fragment3  fragment3 = new Fragment3();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);


        AdapterTest adapterTest = new AdapterTest(getSupportFragmentManager(),list);
        mViewPager.setAdapter(adapterTest);
        mTabLayout.setupWithViewPager(mViewPager);

        BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.aaa272439);
        Bitmap bitmap2 = bitmapDrawable.getBitmap();


        imageView = (ImageView)findViewById(R.id.imaTest);
        imageView.setImageBitmap(Circle_header.getInstance().Circle_head(bitmap2, 0.1f));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(R.style.ActionSheetStyleiOS7);
                changeHead();
            }
        });

        //        箭头监听事件
        info_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



 /* /*//*透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*//**//**//**//**/

/*
//        使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到ToolBar上则不会显示
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.info_collapsing_toolbar_layout);
        mCollapsingToolbarLayout.setTitle(getResources().getString(R.string.info_title));
//        通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后ToolBar上字体的颜色


    }*/

    public void changeHead()
    {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle(R.string.cancel)
                .setOtherButtonTitles(getResources().getString(R.string.changeHead))
                .setCancelableOnTouchOutside(true).setListener(new ActionSheet.ActionSheetListener() {
            @Override
            public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

            }

            @Override
            public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                changeHeadSelect();
            }
        }).show();
    }
    public void changeHeadSelect()
    {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle(R.string.cancel)
                .setOtherButtonTitles(getResources().getString(R.string.take_photo),
                        getResources().getString(R.string.select_xiang_ce))
                .setCancelableOnTouchOutside(true).setListener(new ActionSheet.ActionSheetListener() {
            @Override
            public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

            }

            @Override
            public void onOtherButtonClick(ActionSheet actionSheet, int index) {

            }
        }).show();
    }
}
