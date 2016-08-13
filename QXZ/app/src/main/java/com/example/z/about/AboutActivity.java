package com.example.z.about;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.z.qxz.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AboutActivity  extends AppCompatActivity   implements View.OnClickListener
{

    public static final String VIDEO_NAME = "welcome_video.mp4";
    private VideoView mVideoView;
    private Button about_play,about_back;
    private ViewGroup container;
    private TextView tx1,tx2,tx3,tx4;
    AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            参考这个http://www.jianshu.com/p/f8374d6267ef
//            http://www.jianshu.com/p/34a8b40b9308
        }
        setContentView(R.layout.about);
        getSupportActionBar().hide();
        findView();
        initView();

        File videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists())
        {
            videoFile = copyVideoFile();
        }

        playVideo(videoFile);

        playAnim();

    }

    private void findView()
    {
        mVideoView = (VideoView) findViewById(R.id.videoView);
        about_back = (Button) findViewById(R.id.back);
        about_play = (Button) findViewById(R.id.play);
        container = (ViewGroup) findViewById(R.id.container);

        tx1 = (TextView) findViewById(R.id.tx1);
        tx2 = (TextView) findViewById(R.id.tx2);
        tx3 = (TextView) findViewById(R.id.tx3);
        tx4 = (TextView) findViewById(R.id.tx4);
    }

    private void initView()
    {
        about_play.setOnClickListener(this);
        about_back.setOnClickListener(this);
    }

    private void playVideo(File videoFile)
    {
        mVideoView.setVideoPath(videoFile.getPath());
//        -1填满父控件 -2自身内容尺寸
        mVideoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.setLooping(false);
                mp.start();
            }
        });
    }

    private void playAnim()
    {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(tx1, "alpha", 0,1);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tx2, "alpha", 0,1);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(tx3, "alpha", 0,1);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(tx4, "alpha", 0,1);
//        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(4000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(anim1).before(anim2);
        animatorSet.play(anim2).before(anim3);
        animatorSet.play(anim3).before(anim4);
        animatorSet.start();

        /*anim1.setRepeatCount(1);
        anim1.setRepeatMode(ObjectAnimator.REVERSE);
        anim1.start();*/
        anim1.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                tx1.setVisibility(View.INVISIBLE);
                tx2.setVisibility(View.VISIBLE);
                tx2.setText(R.string.about_tx2);
            }
        });
        anim2.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                tx2.setVisibility(View.INVISIBLE);
                tx3.setVisibility(View.VISIBLE);
                tx3.setText(R.string.about_tx3);
            }
        });
        anim3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tx3.setVisibility(View.INVISIBLE);
                tx4.setVisibility(View.VISIBLE);
                tx4.setText(R.string.about_tx4);
            }
        });
    }

    private File copyVideoFile()
    {
        File videoFile;
        try
        {
            FileOutputStream fos = openFileOutput(VIDEO_NAME,MODE_PRIVATE);
            InputStream in = getResources().openRawResource(R.raw.welcome_video);
            byte [] buff = new byte[1024];
            int len = 0;
            while((len = in.read(buff)) != -1)
            {
                fos.write(buff,0,len);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEO_NAME);
        if(!videoFile.exists())
            throw new RuntimeException("video file has problem");
        return videoFile;
    }
    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id)
        {
            case R.id.play:
//              cancel()防止用户中间再次点击播放造成动画重叠，在这里使用clearAnimation没有效果
                animatorSet.cancel();
                tx1.setVisibility(View.INVISIBLE);
                tx2.setVisibility(View.INVISIBLE);
                tx3.setVisibility(View.INVISIBLE);
                tx4.setVisibility(View.INVISIBLE);
                tx1.setVisibility(View.VISIBLE);
                File videoFile = getFileStreamPath(VIDEO_NAME);
                if (!videoFile.exists())
                {
                    videoFile = copyVideoFile();
                }
                playVideo(videoFile);
                playAnim();
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
