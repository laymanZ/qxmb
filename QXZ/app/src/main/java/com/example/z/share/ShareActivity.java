package com.example.z.share;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.z.qxz.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareActivity extends Activity implements PlatformActionListener
{



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.share_total);
        ShareSDK.initSDK(this);
        initView();

    }

    private  void initView()
    {

        final  ShareEntity shareEntity = new ShareEntity(this);
        shareEntity.setCancelButtonOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                shareEntity.dismiss();
            }
        });
        shareEntity.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                HashMap<String,Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
                if(item.get("ItemText").equals("微博"))
                {
//                    ShareParams shareParams = new ShareParams();
//                    shareParams.setText("在这里说你想说的话~~");
////                    shareParams.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
//
//                    shareParams.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");
//                    Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//                    sinaWeibo.setPlatformActionListener(ShareActivity.this); // 设置分享事件回调
//                    // 执行分享
//                    Log.v("test","haha");
//                    sinaWeibo.share(shareParams);
//                    Log.v("test", "haha2");
                    //2、设置分享内容
                    ShareParams sp = new ShareParams();
                    sp.setText("我是分享文本，啦啦啦"); //分享文本
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");//网络图片url

                    //3、非常重要：获取平台对象
                    Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                    sinaWeibo.setPlatformActionListener(ShareActivity.this); // 设置分享事件回调
                    // 执行分享
                    sinaWeibo.share(sp);

                }
                else if (item.get("ItemText").equals("微信好友"))
                {

                    ShareParams sp = new ShareParams();
                    sp.setShareType(Platform.SHARE_WEBPAGE);
                    sp.setTitle("我是分享标题");  //分享标题
                    sp.setText("我是分享文本，啦啦啦~");
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
                    sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

                    Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                    wechat.setPlatformActionListener(ShareActivity.this);
                    // 执行分享
                    wechat.share(sp);


                }
                else if (item.get("ItemText").equals("朋友圈"))
                {

                    ShareParams sp = new ShareParams();
                    sp.setShareType(Platform.SHARE_WEBPAGE);
                    sp.setTitle("我是分享标题");  //分享标题
                    sp.setText("我是分享文本，啦啦啦~");
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
                    sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情


                    Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                    wechatMoments.setPlatformActionListener(ShareActivity.this);

                    wechatMoments.share(sp);

                }
                else if (item.get("ItemText").equals("QQ"))
                {

                    ShareParams sp = new ShareParams();
                    sp.setTitle("我是分享标题");
                    sp.setText("我是分享文本，啦啦啦~");
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
                    sp.setTitleUrl("http://www.baidu.com");

                    Platform qq = ShareSDK.getPlatform(QQ.NAME);
                    qq.setPlatformActionListener(ShareActivity.this);

                    qq.share(sp);

                }
                shareEntity.dismiss();
            }
        });
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap)
    {
        if (platform.getName().equals(SinaWeibo.NAME))
        {// 判断成功的平台是不是新浪微博
            handler.sendEmptyMessage(1);
        }
        else if (platform.getName().equals(Wechat.NAME))
        {
            handler.sendEmptyMessage(1);
        }
        else if (platform.getName().equals(WechatMoments.NAME))
        {
            handler.sendEmptyMessage(3);
        }
        else if (platform.getName().equals(QQ.NAME))
        {
            handler.sendEmptyMessage(4);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable)
    {
        throwable.printStackTrace();
        Message msg = new Message();
        msg.what = 6;
        msg.obj = throwable.getMessage();
        handler.sendMessage(msg);
    }

    @Override
    public void onCancel(Platform platform, int i)
    {
        handler.sendEmptyMessage(5);
    }

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    Toast.makeText(getApplicationContext(), "微博分享成功", Toast.LENGTH_LONG).show();
                    break;

                case 2:
                    Toast.makeText(getApplicationContext(), "微信分享成功", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), "朋友圈分享成功", Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(), "QQ分享成功", Toast.LENGTH_LONG).show();
                    break;

                case 5:
                    Toast.makeText(getApplicationContext(), "取消分享", Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    Toast.makeText(getApplicationContext(), "分享失败啊" + msg.obj, Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };

}
