package com.weixinlite.android;

import org.litepal.crud.DataSupport;

/**
 * Created by a on 2017/3/15 0015.
 */

public class Friends extends DataSupport implements Comparable<Friends>{

    private int id;
    private String name;
    private int imageId;
    private String msg;
    private String time;
    private boolean istop = false;//判断是否置顶

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getIstop() {
        return istop;
    }

    public void setIstop(boolean istop) {
        this.istop = istop;
    }

    @Override
    public int compareTo(Friends another) {
        //return this.getName().compareTo(another.getName());
        return this.getTime().compareTo(another.getTime());
    }
}
