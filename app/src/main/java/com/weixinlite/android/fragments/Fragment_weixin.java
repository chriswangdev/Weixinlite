package com.weixinlite.android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weixinlite.android.R;

/**
 * Created by a on 2017/3/16 0016.
 */

public class Fragment_weixin extends Fragment{

    private static Fragment_weixin fragment_weixin;

    public static Fragment_weixin newInstance () {
        if (fragment_weixin == null) {
            fragment_weixin = new Fragment_weixin();
        }
        return  fragment_weixin;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weixin,container,false);
        return view;
    }
}
