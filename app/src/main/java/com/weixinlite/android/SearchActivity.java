package com.weixinlite.android;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setNavigationIcon(R.mipmap.backup);
        Drawable drawableLeft = getResources().getDrawable(R.drawable.search);
        drawableLeft.setBounds(0, 0, 80, 80);//android.R.attr.actionBarSize, android.R.attr.actionBarSize);
        EditText editText = (EditText) findViewById(R.id.search_input);
        editText.setCompoundDrawables(drawableLeft, null, null, null);
        setSupportActionBar(toolbar);

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
