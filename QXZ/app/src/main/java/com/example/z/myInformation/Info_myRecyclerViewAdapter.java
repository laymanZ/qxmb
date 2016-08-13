package com.example.z.myInformation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.qxz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z on 2016/3/30.
 */
public class Info_myRecyclerViewAdapter extends RecyclerView.Adapter<MyInfoViewHolder>
{
    private List<Integer> datas;
    private Context context;
    private List<Integer> lists;

    public Info_myRecyclerViewAdapter(Context context, List<Integer> datas)
    {
        this.context = context;
        this.datas = datas;
        getRandomHeights(datas);
    }
    private void getRandomHeights(List <Integer> datas)
    {
        lists = new ArrayList<>();
        for(int i=0;i<datas.size();i++)
        {
            lists.add((int) (200 + Math.random() * 400));
        }
    }

    @Override
    public MyInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.info_layout_item, parent, false);
        MyInfoViewHolder viewHolder = new MyInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(MyInfoViewHolder holder, int position)
    {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = lists.get(position);
        holder.itemView.setLayoutParams(params);
        holder.mTextView.setText(position + "");
    }
}
class MyInfoViewHolder extends RecyclerView.ViewHolder
{
    TextView mTextView;

    public MyInfoViewHolder(View itemView)
    {
        super(itemView);
        mTextView = (TextView)itemView.findViewById(R.id.info_item_tv);
    }
}