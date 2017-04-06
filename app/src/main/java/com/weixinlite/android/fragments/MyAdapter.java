package com.weixinlite.android.fragments;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weixinlite.android.Friends;
import com.weixinlite.android.R;
import com.weixinlite.android.util.Gettime;

import java.util.List;

/**
 * Created by a on 2017/3/17 0017.
 */

public class MyAdapter extends BaseAdapter {


    private static final String TAG = "MyAdapter";

    private List<Friends> friendsList;
    private Context mContext;
    private int length;

    private String time = "";
    private String timelite = "";

    public MyAdapter(Context context, List<Friends> friendsList) {
        mContext = context;
        this.friendsList = friendsList;
    }

    @Override
    public int getCount() {
        return friendsList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mylist_item, parent,
                    false);
            viewHolder.item_all = (RelativeLayout) convertView.findViewById(R.id.item_all);
            viewHolder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.item_msg = (TextView) convertView.findViewById(R.id.item_msg);
            viewHolder.item_time = (TextView) convertView.findViewById(R.id.item_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Log.e(TAG, "getView: ----  position = " + position);
        //viewHolder.item_img.setImageResource(friendsList.get(position).getImageId());

        if (friendsList.get(position).getIstop()) {
            //如果置顶
            viewHolder.item_all.setBackgroundResource(R.color.qianhuise);
        } else {
            //取消置顶
            viewHolder.item_all.setBackgroundResource(R.drawable.item_clicked);
        }

        Glide.with(mContext).load(friendsList.get(position).getImageId()).into(viewHolder.item_img);
        viewHolder.item_name.setText(friendsList.get(position).getName());
        length = friendsList.get(position).getMsgList().size();
        viewHolder.item_msg.setText(friendsList.get(position).getMsgList().get(length - 1).getMsg());

        time = friendsList.get(position).getMsgList().get(length - 1).getMsgtime();
        timelite = time.substring(14,22);
        if (Gettime.getDiffer(Gettime.getNowTime(),time) > 86400000) {
            viewHolder.item_time.setText(time);
        } else {
            viewHolder.item_time.setText(timelite);
        }
        //viewHolder.item_time.setText(friendsList.get(position).getMsgList().get(length - 1).getMsgtime());

        return convertView;
    }

    public class ViewHolder {

        RelativeLayout item_all;
        ImageView item_img;
        TextView item_name;
        TextView item_msg;
        TextView item_time;

    }


}

