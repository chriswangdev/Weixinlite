package com.weixinlite.android;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weixinlite.android.util.Gettime;
import com.weixinlite.android.util.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by a on 2017/3/28 0028.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private static final String TAG = "ChatAdapter";
    private List<Msg> msgList;
    private Context mContext;
    private Friends friends;

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());*/
        String time = Gettime.getNowTime();
        msg.setMsgtime(time);
        Log.e(TAG, "onBindViewHolder: --- time = " + time);
        holder.textView_time.setText(time);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            holder.chat_my_img.setVisibility(View.GONE);
            holder.chat_msg_my.setVisibility(View.GONE);
            holder.chat_friend_img.setVisibility(View.VISIBLE);
            holder.chat_msg.setVisibility(View.VISIBLE);
            //holder.chat_friend_img.setImageResource(msg.getImageId());
            Glide.with(mContext).load(friends.getImageId()).into(holder.chat_friend_img);
            holder.chat_msg.setText(msg.getMsg());
        } else {
            holder.chat_friend_img.setVisibility(View.GONE);
            holder.chat_msg.setVisibility(View.GONE);
            holder.chat_my_img.setVisibility(View.VISIBLE);
            holder.chat_msg_my.setVisibility(View.VISIBLE);
            //holder.chat_my_img.setImageResource(msg.getImageId());
            Glide.with(mContext).load(Utility.myImageId).into(holder.chat_my_img);
            holder.chat_msg_my.setText(msg.getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }



    public ChatAdapter(List<Msg> msgList, Context context, Friends friends) {
        this.msgList = msgList;
        this.mContext = context;
        this.friends = friends;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_time;
        ImageView chat_friend_img;
        TextView chat_msg;
        TextView chat_msg_my;
        ImageView chat_my_img;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_time = (TextView) itemView.findViewById(R.id.chat_time);
            chat_friend_img = (ImageView) itemView.findViewById(R.id.chat_friend_img);
            chat_msg = (TextView) itemView.findViewById(R.id.chat_msg);
            chat_msg_my = (TextView) itemView.findViewById(R.id.chat_msg_my);
            chat_my_img = (ImageView) itemView.findViewById(R.id.chat_my_img);
        }
    }
}
