package com.example.z.share;


import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.z.qxz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShareEntity
{
    private AlertDialog alertDialog;
    private GridView gridView;
    private RelativeLayout cancelButton;
    private SimpleAdapter simpleAdapter;
    private int [] image = {R.drawable.logo_sinaweibo,R.drawable.logo_wechat,R.drawable.logo_wechatmoments,R.drawable.qxlogoqqtest};
    private String[] name = {"微博", "微信好友", "朋友圈", "QQ"};

    public ShareEntity(Context context)
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.share_all);
        gridView = (GridView)window.findViewById(R.id.share_gridView);
        cancelButton = (RelativeLayout)window.findViewById(R.id.share_cancel);

        List<HashMap<String,Object>> shareList = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<image.length;i++)
        {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("ItemImage",image[i]);
            map.put("ItemText",name[i]);
            shareList.add(map);
        }


        simpleAdapter = new SimpleAdapter(context,shareList,R.layout.share_item,new String[]{"ItemImage","ItemText"},new int[]{R.id.shareImg,R.id.shareText});
        gridView.setAdapter(simpleAdapter);


    }
    public void setCancelButtonOnClickListener(View.OnClickListener listener)
    {
        cancelButton.setOnClickListener(listener);
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener)
    {
        gridView.setOnItemClickListener(listener);
    }

    public void dismiss()
    {
        alertDialog.dismiss();
    }
}
