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

public class Fragment_wo extends Fragment{

    private static Fragment_wo fragment_wo;

    public static Fragment_wo newInstance () {
        if (fragment_wo == null) {
            fragment_wo = new Fragment_wo();
        }
        return  fragment_wo;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wo,container,false);
        return view;
    }
}
