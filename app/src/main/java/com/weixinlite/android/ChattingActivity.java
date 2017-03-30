package com.weixinlite.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2017/3/27 0027.
 */

public class ChattingActivity extends AppCompatActivity {

    private static final String TAG = "ChattingActivity";
    private RecyclerView recyclerViewMsg;
    private Friends friends;
    //private List<Friends> listfromlitepal;
    private List<Msg> chatmsgList = new ArrayList<>();

    private Button btn_send;
    private ToggleButton toggleButton;
    private EditText chat_input;
    private Msg msg_send;
    private String msg_to_send;

    private ChatAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initToolBar();

        btn_send = (Button) findViewById(R.id.chat_send);
        toggleButton = (ToggleButton) findViewById(R.id.chat_togglebutton);
        chat_input = (EditText) findViewById(R.id.chat_input);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg_to_send = chat_input.getText().toString();
                if (!TextUtils.isEmpty(msg_to_send)) {
                    msg_send = new Msg(msg_to_send, !toggleButton.isChecked());
                    chatmsgList.add(msg_send);
                    adapter.notifyItemInserted(chatmsgList.size());
                    recyclerViewMsg.scrollToPosition(chatmsgList.size() - 1);
                    chat_input.setText("");
                    Log.e(TAG, "onClick: ----size = " + chatmsgList.size());
                }
            }
        });


    }

    //private String friendsname;

    private void initToolBar() {
        /*friends = DataSupport.where("name = ?", ((Friends) getIntent().getSerializableExtra
                ("friends")).getName()).find(Friends.class).get(0);*/
        friends = (Friends) getIntent().getSerializableExtra("friends");

        //Log.e(TAG, "initToolBar: --- friendsname = " + friendsname);
        //friends = DataSupport.find(Friends.class,1);
        //friends = (Friends) DataSupport.where("name = ?",friendsname).find(Friends.class);
        Toolbar toolbar_chat = (Toolbar) findViewById(R.id.tool_bar);
        TextView textView = (TextView) findViewById(R.id.tool_bar_text);
        toolbar_chat.setTitle("");
        textView.setText(friends.getName());
        toolbar_chat.setNavigationIcon(R.mipmap.backup);
        setSupportActionBar(toolbar_chat);

        recyclerViewMsg = (RecyclerView) findViewById(R.id.msg_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMsg.setLayoutManager(layoutManager);
        Log.e(TAG, "onCreateView: ----------- friends.getMsgList() = " + friends.getMsgList().size());
        chatmsgList.addAll(friends.getMsgList());//chatmsgList = friends.getMsgList();
        Log.e(TAG, "onCreateView: ----------- chatmsgList = " + chatmsgList.size());
        adapter = new ChatAdapter(chatmsgList, this, friends);
        recyclerViewMsg.setAdapter(adapter);
        recyclerViewMsg.scrollToPosition(chatmsgList.size() - 1);
    }

    private Friends friendstosave;

    @Override
    protected void onPause() {
        super.onPause();

        /*friendstosave = new Friends();
        friendstosave.setName(friends.getName());
        friendstosave.setMsgList(chatmsgList);
        friendstosave.save();*/

        friends.setName(friends.getName());
        friends.setMsgList(chatmsgList);
        friends.save();

        Log.e(TAG, "onPause: -------- chatmsgList.size = " + chatmsgList.size());

        for (Msg msg : chatmsgList) {
            msg.setMsg(msg.getMsg());
            msg.setType(msg.getType());
            msg.setMsgtime(msg.getMsgtime());
            msg.setFriendName(friends.getName());
            msg.save();
            /*friends.getMsg().setMsg(msg.getMsg());
            friends.getMsg().setType(msg.getType());
            friends.getMsg().setMsgtime(msg.getMsgtime());
            friends.getMsg().setFriendName(friends.getName());
            friends.getMsg().save();*/
        }

        //DataSupport.saveAll(friends.getMsgList());
        //保存单个friend
        //friends.setIstop(false);
        //friends.update("name = ? ",friends.getName());
        //friends.updateAll("name = ? ",friends.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return true;
        //return super.onOptionsItemSelected(item);
    }
}
