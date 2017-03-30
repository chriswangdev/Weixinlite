package com.weixinlite.android.util;

import android.content.Context;

/**
 * Created by a on 2017/3/29 0029.
 */

public class Utility {

    //public static

    public static int getImageId(String name, Context context) {
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName
                ());
        return id;
    }

    public static int myImageId;

}
