package com.zf.myapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.meituan.android.walle.WalleChannelReader;
import com.zf.base.mvp.view.BaseActivity;
import com.zf.base.tool.BusinessCommon;
import com.zf.contract.MainContract;
import com.zf.utils.ChannelUtil;

public class MainActivity extends BaseActivity<MainContract.Prensenter> implements MainContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("afddadf", ChannelUtil.getChannel(this,"123"));
    }

    @Override
    public MainContract.Prensenter createPresenter() {
        return new MainContract.Prensenter(this);
    }

    public void test1(View view) {
        BusinessCommon.jumpToPhotoAlbum(this);
        mPresenter.getPublicAccountList();
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
//        Toast.makeText(this, ChannelUtil.getChannel(this,"123--"+channel),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void doSomething() {

    }
}
