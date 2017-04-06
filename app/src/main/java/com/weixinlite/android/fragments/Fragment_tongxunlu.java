package com.weixinlite.android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.weixinlite.android.Friends;
import com.weixinlite.android.Msg;
import com.weixinlite.android.R;
import com.weixinlite.android.XiangxiActivity;
import com.weixinlite.android.util.Gettime;
import com.weixinlite.android.util.Utility;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by a on 2017/3/16 0016.
 */

public class Fragment_tongxunlu extends Fragment {

    private static final String TAG = "Fragment_tongxunlu";

    private static Fragment_tongxunlu fragment_tongxunlu;
    private ListView listView;
    private Button btn_save;
    private List<Friends> friendsList = new ArrayList<>();
    private List<Friends> list = new ArrayList<>();
    private List<Msg> msgList = new ArrayList<>();
    private TxlAdapter txlAdapter;

    public static Fragment_tongxunlu newInstance() {
        if (fragment_tongxunlu == null) {
            fragment_tongxunlu = new Fragment_tongxunlu();
        }
        return fragment_tongxunlu;
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tongxunlu, container, false);

        listView = (ListView) view.findViewById(R.id.list_tongxunlu);
        //btn_save = (Button) view.findViewById(R.id.savetolitepal);
        Utility.myImageId = Utility.getImageId("imgmy", getContext());
        findfriends();
        Log.e(TAG, "onCreateView: -----findfriends();------ size = " + friendsList.size());
        if (friendsList.size() == 0) {
            writetolitepal();
            findfriends();
        }

        txlAdapter = new TxlAdapter(getContext(), friendsList);
        listView.setAdapter(txlAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), XiangxiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("friends", friendsList.get(position));
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    private void findfriends() {
        this.friendsList.removeAll(friendsList);
        list = DataSupport.findAll(Friends.class);

        for (int i = 0; i < list.size(); i++) {
            Friends friends = new Friends();
            friends.setName(list.get(i).getName());
            msgList = DataSupport.where("friendName = ?", friends.getName()).find(Msg.class);

            Msg msg = new Msg();
            friends.setIstop(list.get(i).getIstop());
            friends.setImageId(list.get(i).getImageId());
            friends.setMsgList(msgList);
            this.friendsList.add(friends);

        }
    }

    private void writetolitepal() {

        Friends friends = new Friends();

        friends.setImageId(Utility.getImageId("beautyone", getContext()));
        friends.setName("小王");

        List<Msg> msgList = new ArrayList<>();
        Msg msg = new Msg("你好", Msg.TYPE_RECEIVED);
        msg.setFriendName(friends.getName());
        msg.setMsgtime(Gettime.getNowTime());
        msg.save();
        msgList.add(msg);
        friends.setMsgList(msgList);
        friends.save();

        friends = new Friends();
        friends.setImageId(Utility.getImageId("beautytwo", getContext()));
        friends.setName("小丽");
        friends.save();

        friends = new Friends();
        friends.setImageId(Utility.getImageId("beautythree", getContext()));
        friends.setName("小小");
        friends.save();

        friends = new Friends();
        friends.setImageId(Utility.getImageId("beautyfour", getContext()));
        friends.setName("妹子");
        friends.save();

    }


}
