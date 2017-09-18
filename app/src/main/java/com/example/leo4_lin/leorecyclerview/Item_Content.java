package com.example.leo4_lin.leorecyclerview;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Leo4_Lin on 2017/9/18.
 */

public class Item_Content extends  RecyclerView.Adapter<Item_Content.ViewHolder>{

    private List<HashMap<String,String>>  mData;

    public Item_Content(List<HashMap<String,String>> data) {
        mData = data;
    }

    //建構自己的ViewHolder取代原始的
    //設定view元件
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView image;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.info_text);
            image = (ImageView) v.findViewById(R.id.info_img);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //取出先前所找出的所有app
        HashMap data = mData.get(position);
        Drawable icon = (Drawable)data.get("icon");
        String AppName = (String) data.get("appName");
        holder.image.setImageDrawable(icon);
        holder.mTextView.setText(AppName);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
