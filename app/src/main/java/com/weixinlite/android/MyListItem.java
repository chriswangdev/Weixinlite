package com.weixinlite.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by a on 2017/3/15 0015.
 * by yuandiandev
 * 用来显示每个单条的内容
 */

public class MyListItem extends LinearLayout {

    private View view;

    private ImageView imageView;

    private TextView item_name;

    private TextView item_msg;

    private TextView item_time;

    public MyListItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        view = LayoutInflater.from(context).inflate(R.layout.mylist_item, null);

        imageView = (ImageView) view.findViewById(R.id.item_img);
        item_name = (TextView) view.findViewById(R.id.item_name);
        item_msg = (TextView) view.findViewById(R.id.item_msg);
        item_time = (TextView) view.findViewById(R.id.item_time);

    }

    public void setImageView(int resource) {
        imageView.setBackgroundResource(resource);
    }


    public void setName(String name) {
        item_name.setText(name);
    }


    public void setMsg(String msg) {
        item_msg.setText(msg);
    }


    public void setTime(String time) {
        item_time.setText(time);
    }


}
