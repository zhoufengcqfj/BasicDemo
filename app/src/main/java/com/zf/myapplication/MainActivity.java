package com.zf.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.meituan.android.walle.WalleChannelReader;
import com.zf.utils.ChannelUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("afddadf", ChannelUtil.getChannel(this,"123"));
    }

    public void test1(View view) {
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        Toast.makeText(this, ChannelUtil.getChannel(this,"123--"+channel),Toast.LENGTH_SHORT).show();
    }
}
