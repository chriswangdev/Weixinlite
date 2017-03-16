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

public class Fragment_tongxunlu extends Fragment{

    private static Fragment_tongxunlu fragment_tongxunlu;

    public static Fragment_tongxunlu newInstance () {
        if (fragment_tongxunlu == null) {
            fragment_tongxunlu = new Fragment_tongxunlu();
        }
        return  fragment_tongxunlu;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tongxunlu,container,false);
        return view;
    }
}
