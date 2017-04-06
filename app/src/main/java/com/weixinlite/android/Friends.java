package com.weixinlite.android;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2017/3/15 0015.
 */
//实现Comparable接口，进行排序
//
public class Friends extends DataSupport implements Comparable<Friends>, Serializable {

    private int id;
    private String name;
    private int imageId;
    private Msg msg = new Msg();
    //private String time;
    private boolean istop = false;//判断是否置顶
    private List<Msg> msgList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }*/

    public boolean getIstop() {
        return istop;
    }

    public void setIstop(boolean istop) {
        this.istop = istop;
    }

    public List<Msg> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public int compareTo(Friends another) {

        return this.getMsgList().get(this.getMsgList().size() - 1).getMsgtime().compareTo(another
                .getMsgList().get(another.getMsgList().size() - 1).getMsgtime());
        //return this.getName().compareTo(another.getName());
        //return this.msg.getMsgtime().compareTo(another.msg.getMsgtime());//this.getTime()
        // .compareTo(another.getTime());
    }
}
