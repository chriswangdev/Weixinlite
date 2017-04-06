package com.weixinlite.android;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by a on 2017/4/5 0005.
 */

public class XiangxiActivity extends AppCompatActivity {

    private static final String TAG = "XiangxiActivity";

    private Friends friends;
    private ImageView imageView;
    private TextView textView_id;
    private TextView textView_nicheng;

    private Button btnSendMsg;
    private Button btnChatVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangxi);
        initToolBar();
        initLayout();

        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(XiangxiActivity.this,ChattingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("friends",friends);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        btnChatVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XiangxiActivity.this, "视频聊天", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolBar() {

        friends = (Friends) getIntent().getSerializableExtra("friends");
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        TextView textView = (TextView) findViewById(R.id.tool_bar_text);
        textView.setText("详细信息");
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.backup);
        setSupportActionBar(toolbar);

    }

    private void initLayout() {
        imageView = (ImageView) findViewById(R.id.item_img);
        textView_id = (TextView) findViewById(R.id.item_name);
        textView_nicheng = (TextView) findViewById(R.id.item_msg);

        btnSendMsg = (Button) findViewById(R.id.send_msg);
        btnChatVideo = (Button) findViewById(R.id.chat_with_vedio);


        Glide.with(this).load(friends.getImageId()).into(imageView);
        textView_id.setText(friends.getName());
        textView_nicheng.setTextColor(Color.parseColor("#969696"));
        textView_nicheng.setText("昵称：" + friends.getName());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.more_xiangxi:
                Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xiangxi, menu);
        return true;
    }
}
