package com.example.z.qxz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


public class ContentMessage extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_show);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = (TextView)findViewById(R.id.message_content);
        textView.setText("这里是测试的\n"+
                            "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n"+
                "\n"+
                "这里是测试的\n");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
// 当点击ActionBar图标的时候，系统会调用onOptionsItemSelected()方法，并且此时的itemId是android.R.id.home
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
