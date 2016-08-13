package com.example.z.qxz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.z.about.AboutActivity;
import com.example.z.faMenu_Button.FloatingActionButton;
import com.example.z.faMenu_Button.FloatingActionsMenu;
import com.example.z.helper.SimpleItemTouchHelperCallback;
import com.example.z.luntan.IndexOfLunTan;
import com.example.z.myInformation.User_info;
import com.example.z.share.ShareActivity;
import com.example.z.share.ShareEntity;
import com.example.z.skin.SkinActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import ren.solid.skinloader.base.SkinBaseActivity;

//首页的类
public class MainActivity extends SkinBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,PlatformActionListener
{
    private List<CardView> mList;
    private Handler handler = new Handler();
    CardView cardView;
    FloatingActionsMenu menuMultipleActions;
    ImageView user_head_portrait;
    DrawerLayout drawer;

    private ItemTouchHelper mItemTouchHelper;
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isLoading;
    private List<CardView> data = new ArrayList<>();
    RecyclerViewListAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;



    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_main);
        adapter = new RecyclerViewListAdapter(this,data);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefreshLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        initFAB();
        initView();
        initData();
//        --------------------------------------------------------------------------------------
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.aaa272439);
        Bitmap bitmap2 = bitmapDrawable.getBitmap();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

//      要用下面这句话需要在Activity的theme中添加android:theme="@style/AppTheme.NoActionBar" 才可以，否则无法加载Activity
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


/*
        使用注释的这种写法可以消除 NullPointerException ，但是加载用户头像显示不出来，需要改成下面的做法，
        直接通过inflateHeaderView来加载
        LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.nav_header_main, null);
        user_head_portrait = (ImageView)view.findViewById(R.id.user_head_portrait);*/
        //Log.v("haha",user_head_portrait == null ? "kong":"nokong");

//      使用  inflateHeaderView 来加载布局是，由于xml文件中也使用了 app：headerLayout 来加载布局，所以导致结果
//      会显示上下两次，上面是使用 LayoutInflater 加载之后的布局不能成功显示图片，下面是可以成功显示图片的布局，是我们想要的，
//       此时需要将xml文件中的  app:headerLayout="@layout/nav_header_main" 这句话去掉即可
        View view = navigationView.inflateHeaderView(R.layout.nav_header_main);
        user_head_portrait = (ImageView)view.findViewById(R.id.user_head_portrait);
        user_head_portrait.setImageBitmap(Circle_header.getInstance().Circle_head(bitmap2, 0.07f));
        user_head_portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, User_info.class);
                startActivity(intent);

            }
        });

        dynamicAddSkinEnableView(toolbar, "background", R.color.colorPrimary);
        dynamicAddSkinEnableView(navigationView.getHeaderView(0), "background", R.color.colorPrimary);
        dynamicAddSkinEnableView(navigationView, "navigationViewMenu", R.color.colorPrimary);
//      dynamicAddSkinEnableView(menuMultipleActions, "R.styleable.FloatingActionsMenu_fab_addButtonColorNormal", R.color.colorPrimary);

    }

    private void initView()
    {
//        第一句话设置颜色无效
//      swipeRefreshLayout.setColorSchemeColors(R.color.first_color,R.color.second_color);
//      使用getResources.getColor()会报错
        swipeRefreshLayout.setColorScheme(R.color.first_color, R.color.second_color);
        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        Toast.makeText(MainActivity.this,"数据加载完成",Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSignificantDelta;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                fab的出现与隐藏
                isSignificantDelta = Math.abs(dy) > 4;
                if (isSignificantDelta) {
                    if (dy > 0) {
                            /*这个show方法是自己定义的*/
                        menuMultipleActions.show(false);
                    } else {
                        menuMultipleActions.show(true);
                    }
                }


                Log.d("test", "onScrolled");
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        adapter.setOnItemClickListener(new RecyclerViewListAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,ContentMessage.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
//        --------------------------------------------------------------------------------------

        cardView = (CardView)findViewById(R.id.index_card_container);
        mList = new ArrayList<>();
    }

    //FAB-------------------------------------------------------------------------------------------------
    public void initFAB()
    {
        final View actionB = findViewById(R.id.action_b);
        FloatingActionButton actionC = new FloatingActionButton(getBaseContext());
        actionC.setTitle("Hide/Show Action above");
        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionB.setVisibility(actionB.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.addButton(actionC);

        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                actionA.setTitle("Action A clicked");
            }
        });
        FloatingActionButton addedOnce = new FloatingActionButton(this);
        addedOnce.setTitle("Added once");
        FloatingActionButton addedTwice = new FloatingActionButton(this);
        addedTwice.setTitle("Added twice");
    }



//-------------------------------------------------------------------------------------------------------
    private long lastBackKeyDownTick = 0;
    public static final long MAX_DOUBLE_BACK_DURATION = 1500;
    @Override
    public void onBackPressed()
    {

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        long currentTick = System.currentTimeMillis();
        if(currentTick - lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION)
        {
            Snackbar.make(drawer,"再按一次退出",Snackbar.LENGTH_SHORT).show();
            lastBackKeyDownTick = currentTick;
        }
        else
        {
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//  响应菜单点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {

            case R.id.qx_index:

                break;
            case R.id.about:
                Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.skin:
                Intent intent = new Intent(MainActivity.this, SkinActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                Intent intent2 = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_send:
                Intent intent3 = new Intent(this, IndexOfLunTan.class);
                startActivity(intent3);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        start与left 是同一个方向    end与right是同一个方向
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    -----------------------------------------------------------------------------------

    public void initData()
    {
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                getData();
            }
        }, 1500);

    }
    private void getData()
    {
        for (int i = 0; i < 20; i++)
        {

            data.add(cardView);
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap)
    {
        if (platform.getName().equals(SinaWeibo.NAME))
        {// 判断成功的平台是不是新浪微博
            handler2.sendEmptyMessage(1);
        }
        else if (platform.getName().equals(Wechat.NAME))
        {
            handler2.sendEmptyMessage(1);
        }
        else if (platform.getName().equals(WechatMoments.NAME))
        {
            handler2.sendEmptyMessage(3);
        }
        else if (platform.getName().equals(QQ.NAME))
        {
            handler2.sendEmptyMessage(4);
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable)
    {
        throwable.printStackTrace();
        Message msg = new Message();
        msg.what = 6;
        msg.obj = throwable.getMessage();
        handler2.sendMessage(msg);
    }

    @Override
    public void onCancel(Platform platform, int i)
    {
        handler2.sendEmptyMessage(5);
    }

    private  void initShareView()
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
                    Platform.ShareParams sp = new Platform.ShareParams();
                    sp.setText("我是分享文本，啦啦啦"); //分享文本
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");//网络图片url

                    //3、非常重要：获取平台对象
                    Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                    sinaWeibo.setPlatformActionListener(MainActivity.this); // 设置分享事件回调
                    // 执行分享
                    sinaWeibo.share(sp);

                }
                else if (item.get("ItemText").equals("微信好友"))
                {

                    Platform.ShareParams sp = new Platform.ShareParams();
                    sp.setShareType(Platform.SHARE_WEBPAGE);
                    sp.setTitle("我是分享标题");  //分享标题
                    sp.setText("我是分享文本，啦啦啦~");
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
                    sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情

                    Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                    wechat.setPlatformActionListener(MainActivity.this);
                    // 执行分享
                    wechat.share(sp);


                }
                else if (item.get("ItemText").equals("朋友圈"))
                {

                    Platform.ShareParams sp = new Platform.ShareParams();
                    sp.setShareType(Platform.SHARE_WEBPAGE);
                    sp.setTitle("我是分享标题");  //分享标题
                    sp.setText("我是分享文本，啦啦啦~");
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
                    sp.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情


                    Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                    wechatMoments.setPlatformActionListener(MainActivity.this);

                    wechatMoments.share(sp);

                }
                else if (item.get("ItemText").equals("QQ"))
                {

                    Platform.ShareParams sp = new Platform.ShareParams();
                    sp.setTitle("我是分享标题");
                    sp.setText("我是分享文本，啦啦啦~");
                    sp.setImageUrl("http://7xrv9y.com1.z0.glb.clouddn.com/qingxie.jpg");
                    sp.setTitleUrl("http://www.baidu.com");

                    Platform qq = ShareSDK.getPlatform(QQ.NAME);
                    qq.setPlatformActionListener(MainActivity.this);

                    qq.share(sp);

                }
                shareEntity.dismiss();
            }
        });
    }
    Handler handler2 = new Handler()
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
