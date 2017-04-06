package com.weixinlite.android;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a on 2017/3/27 0027.
 * for chat
 */

public class Msg extends DataSupport implements Serializable{//extends Friends{

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    public String msgchat;
    public int type;
    public String friendName;

    public String msgtime;
    public long timelong;

    //public int friends_id;

    public Msg() {

    }

    public Msg(String content, int type) {
        this.msgchat = content;
        this.type = type;
    }

    //重载构造器，
    public Msg(String content, boolean type) {
        this.msgchat = content;
        if (type) {
            this.type = TYPE_RECEIVED;
        } else {
            this.type = TYPE_SEND;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msgchat;
    }

    public void setMsg(String msg) {
        this.msgchat = msg;
    }

    public String getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(String msgtime) {
        this.msgtime = msgtime;
    }


    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
