package com.weixinlite.android.fragments;

import android.content.Intent;
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

import com.weixinlite.android.ChattingActivity;
import com.weixinlite.android.Friends;
import com.weixinlite.android.Msg;
import com.weixinlite.android.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    private List<Friends> friendsListsave = new ArrayList<>();
    //存储置顶
    private List<Friends> friendsListTop = new ArrayList<Friends>();
    private List<Friends> friendsListnotTop = new ArrayList<Friends>();
    private List<Msg> msgList = new ArrayList<>();
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weixin, container, false);

        listView = (ListView) view.findViewById(R.id.list_weixin);

        refreshchat();

        friendsListnotTop.addAll(friendsListsave);

        adapter = new MyAdapter(getContext(), friendsListsave);

        //Collections.sort(friendsListsave);//升序
        //Collections.sort(friendsListsave,Collections.<Friends>reverseOrder());//降序

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Log.e(TAG, "onItemClick: ------   position = " + position);
                //Toast.makeText(getActivity(), "------   position = " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ChattingActivity.class);
                //intent.putExtra("toolbar_name",friendsListsave.get(position).getName());
                Bundle bundle = new Bundle();
                bundle.putSerializable("friends",friendsListsave.get(position));
                intent.putExtras(bundle);
                getActivity().startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                    id) {
                setbackgroundalph(0.6f);

                if (friendsListsave.get(position).getIstop()) {
                    arrayadapter = new ArrayAdapter<String>(getContext(), android.R.layout
                            .simple_list_item_1, s2);
                    popuplistView.setAdapter(arrayadapter);
                } else {
                    arrayadapter = new ArrayAdapter<String>(getContext(), android.R.layout
                            .simple_list_item_1, s);
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
                        //Toast.makeText(getActivity(), "置顶聊天", Toast.LENGTH_SHORT).show();
                        if (item_selected != -1) {
                            if (!friendsListsave.get(item_selected).getIstop()) {
                                //未置顶
                                //添加到置顶list

                                //Log.e(TAG, "onItemClick: item_selected = " + item_selected);
                                friendsListnotTop.remove(item_selected - friendsListTop.size());
                                friendsListTop.add(0, friendsListsave.get(item_selected));
                                friendsListTop.get(0).setIstop(true);
                                //friendsListsave.add(0,friendsListsave.get(item_selected));
                                //friendsListsave.get(0).setIstop(true);
                                //friendsListsave.remove(item_selected + 1);


                                friendsListsave.removeAll(friendsListsave);

                                friendsListsave.addAll(friendsListTop);
                                friendsListsave.addAll(friendsListnotTop);
                                //Collections.sort(friendsListsave);

                                listView.setAdapter(adapter);

                            } else {
                                //friendsListsave.get(item_selected).setIstop(false);
                                //Collections.sort(friendsListsave);
                                friendsListTop.get(item_selected).setIstop(false);
                                friendsListnotTop.add(friendsListTop.get(item_selected));
                                friendsListTop.remove(item_selected);
                                
                                Collections.sort(friendsListnotTop);

                                friendsListsave.removeAll(friendsListsave);

                                friendsListsave.addAll(friendsListTop);
                                friendsListsave.addAll(friendsListnotTop);
                                //friendsListsave.addAll(0, friendsListTop);


                                listView.setAdapter(adapter);
                            }
                            item_selected = -1;
                        }
                        break;
                    case 2:
                        // Toast.makeText(getActivity(), "删除该聊天", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onResume() {
        super.onResume();
        refreshchat();
        adapter.notifyDataSetChanged();
    }

    private void refreshchat() {
        friendsListsave.removeAll(friendsListsave);
        List<Friends> list = DataSupport.findAll(Friends.class);

        Log.e(TAG, "onCreateView: ---- size = " + list.size());

        //for (int i = 0; i < list.size(); i++) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Friends friends = new Friends();

            friends.setName(list.get(i).getName());
            Log.e(TAG, "onCreateView: ------- friendName = " + friends.getName());
            msgList = DataSupport.where("friendName = ?", friends.getName()).find(Msg.class);
            Log.e(TAG, "onCreateView: ----------- msgList = " + msgList.size());
            if (msgList.size() > 0) {
                Msg msg = new Msg();
                friends.setIstop(list.get(i).getIstop());
                friends.setImageId(list.get(i).getImageId());

                friends.setMsgList(msgList);
                friendsListsave.add(friends);
            }
        }
    }



    /*int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);*/
    private ArrayAdapter<String> arrayadapter;
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
