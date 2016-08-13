package com.example.z.qxz;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.helper.ItemTouchHelperAdapter;
import com.example.z.helper.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

public class RecyclerViewListAdapter extends RecyclerView.Adapter<ViewHolder>
    implements ItemTouchHelperAdapter
{

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;
    private List data;
    private OnItemClickListener onItemClickListener;


    public RecyclerViewListAdapter(Context context, List data)
    {
        this.context = context;
        this.data = data;
    }



    public interface OnItemClickListener
    {
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == TYPE_ITEM)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.index_content_item, parent, false);
            return new ItemViewHolder(view);
        }
        else if (viewType == TYPE_FOOTER)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (holder instanceof ItemViewHolder)
        {
            ((ItemViewHolder) holder).textView.setText("haha");
            if (onItemClickListener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount()
    {
        return data.size() == 0 ? 0 : data.size() + 1;
    }


    @Override
    public int getItemViewType(int position)
    {
        if (position + 1 == getItemCount())
        {
            return TYPE_FOOTER;
        }
        else
        {
            return TYPE_ITEM;
        }
    }



    @Override
    public void onItemMove(int fromPosition, int toPosition)
    {

        swapPositions(data,fromPosition,toPosition);
        data.remove(fromPosition);
        //如果开启的拖动移动位置的功能
        //要重写这个方法  因为如果不重写  交换的只是view的位置，数据的位置没有交换 一拖动，就会变成原来的样子

        notifyItemMoved(fromPosition, toPosition);
    }

    public void swapPositions(List<?> list, int from, int to)
    {
//        if (customHeaderView != null)
//        {
//            from--;
//            to--;
//        }
        Collections.swap(list, from, to);
    }
    @Override
    public void onItemDismiss(int position)
    {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public static class ItemViewHolder extends ViewHolder implements
            ItemTouchHelperViewHolder
    {

        public final CardView cardView;
        TextView textView;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            cardView = (CardView) itemView;
            textView = (TextView)cardView.findViewById(R.id.tv_test);
        }
//      选中变色
        @Override
        public void onItemSelected() {}

//      上下移动或者左右滑动变色
        @Override
        public void onItemClear() {}
    }

    static class FootViewHolder extends ViewHolder
    {

        public FootViewHolder(View view)
        {
            super(view);
        }
    }
}
