package com.weixinlite.android;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<MyView> myViews = new ArrayList<>();
    private MyView myView_weixin;
    private MyView myView_tongxunlu;
    private MyView myView_faxian;
    private MyView myView_wo;

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initBottomviews();
        initViewPagers();

        FragmentManager fragmentManager = getSupportFragmentManager();

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentManager);

        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new MyPageChangeListener());
        viewPager.setCurrentItem(0);
    }

    private void initBottomviews() {
        myView_weixin = (MyView) findViewById(R.id.weixin);
        myView_tongxunlu = (MyView) findViewById(R.id.tongxunlu);
        myView_faxian = (MyView) findViewById(R.id.faxian);
        myView_wo = (MyView) findViewById(R.id.wo);

        myView_weixin.setOnClickListener(this);
        myView_tongxunlu.setOnClickListener(this);
        myView_faxian.setOnClickListener(this);
        myView_wo.setOnClickListener(this);

        myViews.add(myView_weixin);
        myViews.add(myView_tongxunlu);
        myViews.add(myView_faxian);
        myViews.add(myView_wo);

        myView_weixin.setAlpha(1f);
    }

    private void initViewPagers() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tongxunlu:
                viewPager.setCurrentItem(1);
                break;
            case R.id.faxian:
                viewPager.setCurrentItem(2);
                break;
            case R.id.wo:
                viewPager.setCurrentItem(3);
                break;
            default:
                break;

        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset > 0) {
                MyView leftView = myViews.get(position);
                leftView.setAlpha(1 - positionOffset);


                MyView rightView = myViews.get(position + 1);
                rightView.setAlpha(positionOffset);
            }
            if (position > 4) {
                viewPager.setCurrentItem(1);
            }


        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.more:
                Toast.makeText(this, "More", Toast.LENGTH_SHORT).show();
                break;
            default:
        }

        return true;
    }
}
