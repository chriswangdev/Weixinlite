package com.weixinlite.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.weixinlite.android.fragments.Fragment_faxian;
import com.weixinlite.android.fragments.Fragment_tongxunlu;
import com.weixinlite.android.fragments.Fragment_weixin;
import com.weixinlite.android.fragments.Fragment_wo;

/**
 * Created by a on 2017/3/16 0016.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragment_weixin.newInstance();
            case 1:
                return Fragment_tongxunlu.newInstance();
            case 2:
                return Fragment_faxian.newInstance();
            case 3:
                return Fragment_wo.newInstance();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
