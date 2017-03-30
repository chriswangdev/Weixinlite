package com.weixinlite.android.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weixinlite.android.Friends;
import com.weixinlite.android.R;

import java.util.List;

/**
 * Created by a on 2017/3/29 0029.
 */

public class TxlAdapter extends BaseAdapter{

    private List<Friends> friendsList;
    private Context mContext;

    public TxlAdapter (Context context,List<Friends> friendsList) {
        this.friendsList = friendsList;
        this.mContext = context;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mylist_item, parent, false);
            viewHolder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.item_name = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.item_msg = (TextView) convertView.findViewById(R.id.item_msg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(friendsList.get(position).getImageId()).into(viewHolder.item_img);
        viewHolder.item_name.setText(friendsList.get(position).getName());
        viewHolder.item_msg.setVisibility(View.GONE);

        return convertView;
    }

    class ViewHolder {
        ImageView item_img;
        TextView item_name;
        TextView item_msg;
    }
}
