package com.weixinlite.android.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.weixinlite.android.Friends;
import com.weixinlite.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a on 2017/3/16 0016.
 */

public class Fragment_weixin extends Fragment {


    private static final String TAG = "Fragment_weixin";

    private static Fragment_weixin fragment_weixin;

    public static Fragment_weixin newInstance() {
        if (fragment_weixin == null) {
            fragment_weixin = new Fragment_weixin();
        }
        return fragment_weixin;
    }

    private ListView listView;
    private ListView popuplistView;
    private PopupWindow popupWindow;

    //判断选中的listitem
    private int item_selected = -1;

    private List<Friends> friendsListsave = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weixin, container, false);

        listView = (ListView) view.findViewById(R.id.list_weixin);

        /*SimpleAdapter adapter = new SimpleAdapter(getContext(), getData(), R.layout.mylist_item,
                new String[]{"imageid", "name", "msg", "time"}, new int[]{R.id.item_img, R.id
                .item_name, R.id.item_msg, R.id.item_time});*/

        if (friendsListsave == null) {
            friendsListsave = getData();
        }

        final MyAdapter adapter = new MyAdapter(getContext(), friendsListsave);

        listView.setAdapter(adapter);

        if (popupWindow == null) {
            initpopupwindow();
        }


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setbackgroundalph(1f);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                    id) {
                setbackgroundalph(0.6f);

                if (friendsListsave.get(position).getIstop()) {
                    arrayadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, s2);
                    popuplistView.setAdapter(arrayadapter);
                } else {
                    arrayadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, s);
                    popuplistView.setAdapter(arrayadapter);
                }

                popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

                item_selected = position;

                return true;
            }
        });

        popuplistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "clicked " + position, Toast.LENGTH_SHORT).show();

                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "标为未读", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "置顶聊天", Toast.LENGTH_SHORT).show();
                        if (item_selected != -1) {
                            friendsListsave.add(0,friendsListsave.get(item_selected));

                            if (!friendsListsave.get(position).getIstop()) {
                                friendsListsave.get(0).setIstop(true);
                            } else {
                                friendsListsave.get(0).setIstop(false);
                            }

                            friendsListsave.remove(item_selected + 1);

                            listView.setAdapter(adapter);
                            item_selected = -1;
                        }
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "删除该聊天", Toast.LENGTH_SHORT).show();
                        if (item_selected != -1) {
                            //删除该聊天
                            friendsListsave.remove(item_selected);
                            listView.setAdapter(adapter);
                            //listView.invalidate();

                            item_selected = -1;
                        }
                        break;
                    default:
                        break;
                }

                popupWindow.dismiss();
            }
        });

        return view;
    }

    private List<Friends> getData() {
        List<Friends> list = new ArrayList<>();

        Friends friends = new Friends();

        friends.setImageId(R.drawable.beautyone);
        friends.setName("小王");
        friends.setMsg("你好");
        friends.setTime("10:05");
        friends.setIstop(false);
        list.add(friends);

        for (int i = 0; i < 6; i++) {

            friends = new Friends();
            friends.setImageId(R.drawable.beautytwo);
            friends.setName("小丽" + i);
            friends.setMsg("你好");
            friends.setTime("10:20");
            friends.setIstop(false);
            list.add(friends);


            friends = new Friends();
            friends.setImageId(R.drawable.beautythree);
            friends.setName("小小" + i);
            friends.setMsg("阿什顿飞");
            friends.setTime("15:45");
            friends.setIstop(false);
            list.add(friends);


            friends = new Friends();
            friends.setImageId(R.drawable.beautyfour);
            friends.setName("妹子" + i);
            friends.setMsg("隧道股份");
            friends.setTime("17:05");
            friends.setIstop(false);
            list.add(friends);

        }

        return list;
    }

    /*private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("imageid", R.drawable.beautyone);
        map.put("name", "小啊");
        map.put("msg", "你好啊");
        map.put("time", "10:05");
        list.add(map);

        map = new HashMap<>();
        map.put("imageid", R.drawable.beautytwo);
        map.put("name", "富贵花");
        map.put("msg", "哈哈哈哈");
        map.put("time", "12:13");
        list.add(map);

        map = new HashMap<>();
        map.put("imageid", R.drawable.beautythree);
        map.put("name", "很快乐");
        map.put("msg", "。。。。");
        map.put("time", "14:22");
        list.add(map);

        map = new HashMap<>();
        map.put("imageid", R.drawable.beautyfour);
        map.put("name", "闪电狗");
        map.put("msg", "塑料袋");
        map.put("time", "17:01");
        list.add(map);

        return list;
    }*/

    /*int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);*/
    private ArrayAdapter<String> arrayadapter ;
    private View mview;
    private void initpopupwindow() {
         mview = getActivity().getLayoutInflater().inflate(R.layout.pop_list, null);

        popuplistView = (ListView) mview.findViewById(R.id.pop_list);
        /*adapter = new ArrayAdapter<String>(getContext(), android.R.layout
                .simple_list_item_1, s);
        popuplistView.setAdapter(adapter);*/

        popupWindow = new PopupWindow(mview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }


    private void setbackgroundalph(float f) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

    String[] s = new String[]{"标为未读", "置顶聊天", "删除该聊天"};
    String[] s2 = new String[]{"标为未读", "取消置顶", "删除该聊天"};

}
