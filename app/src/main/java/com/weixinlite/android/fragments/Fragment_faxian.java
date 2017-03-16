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

public class Fragment_faxian extends Fragment{

    private static Fragment_faxian fragment_faxian;

    public static Fragment_faxian newInstance () {
        if (fragment_faxian == null) {
            fragment_faxian = new Fragment_faxian();
        }
        return  fragment_faxian;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faxian,container,false);
        return view;
    }
}
