package com.example.z.qxz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

//登录的类
public class User_login extends AppCompatActivity
{
    /*String APP_KEY = "140b4459687f3";
    String APP_SECRECT = "2a4d4fa4882e11af6f301a0555443b5d";
    ImageView user_login_header;
    long exitTime=0;// 退出时间


    Button login_user,regist_user,login_cancel;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //        初始化
        SMSSDK.initSDK(this, APP_KEY, APP_SECRECT);
        setContentView(R.layout.login_layout);
        BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.aaa272439);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        login_user = (Button)findViewById(R.id.login);
        regist_user = (Button)findViewById(R.id.newUser);
        login_cancel = (Button)findViewById(R.id.loginCancle);
        login_user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(User_login.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        regist_user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(User_login.this,Regist.class);
                startActivity(intent);
                finish();
            }
        });
        login_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //        注册手机号
                RegisterPage registerPage = new RegisterPage();

//        注册回调事件
                registerPage.setRegisterCallback(new EventHandler() {
                    //            事件完成后调用
                    @Override
                    public void afterEvent(int event, int result, Object data) {
                        super.afterEvent(event, result, data);
//                判断结果是否已经完成
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            //获取数据data
                            HashMap<String, Object> maps = (HashMap<String, Object>) data;
//                    国家
                            String country = (String) maps.get("country");
//                    手机号
                            String phone = (String) maps.get("phone");
                            submitUserInfo(country, phone);
                            Intent intent = new Intent(User_login.this,MainActivity.class);
                            startActivity(intent);

                        }
                    }
                });
//        显示注册界面
                registerPage.show(User_login.this);
            }
        });

        getSupportActionBar().hide();//隐藏标题栏
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.loginli);
        Animation up = AnimationUtils.loadAnimation(this, R.animator.pingyi);
        linearLayout.startAnimation(up);
        Button button = (Button)findViewById(R.id.login);
        button.startAnimation(up);
        up.setFillAfter(true);

        user_login_header = (ImageView)findViewById(R.id.img);
        user_login_header.setImageBitmap(Circle_header.getInstance().Circle_head(bitmap,0.1f));

        *//*Matrix matrix = new Matrix();
        matrix.setScale(0.1f, 0.1f);
        imageView.setImageMatrix(matrix);*//*
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // TODO 按两次返回键退出应用程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            // 判断间隔时间 大于2秒就退出应用
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                // 应用名
                String applicationName = getResources().getString(R.string.app_name);
                String msg = "再按一次返回键退出" + applicationName;
                //String msg1 = "再按一次返回键回到桌面";
                Toast.makeText(User_login.this, msg,0).show();
                // 计算两次返回键按下的时间差
                exitTime = System.currentTimeMillis();
            }
            else
            {
                // 关闭应用程序
                finish();
                // 返回桌面操作
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    *//*
* 提交用户信息
* *//*
    public void submitUserInfo(String country,String phone)
    {
        Random r = new Random();
        String uid = Math.abs(r.nextInt())+"";
        String nickName = "Z";
        SMSSDK.submitUserInfo(uid,nickName,null,country,phone);
    }*/
}
