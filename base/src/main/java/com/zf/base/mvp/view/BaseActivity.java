package com.zf.base.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zf.base.R;
import com.zf.base.mvp.presenter.BasePresenter;

import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by zhoufeng on 2020/1/16.
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity
        implements BaseView,EasyPermissions.PermissionCallbacks{

    //RxLifecycle对象，每个Activity 调用onDestroy时用来取消观察者的订阅。
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    //Presanter，继承自BasePresenter。
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //构建该Activity的Presenter对象。
        mPresenter = createPresenter();
    }

    public abstract T createPresenter();

    @SuppressWarnings("unchecked")
    @Override
    public final BehaviorSubject getLifeCycleSubject() {
        return lifecycleSubject;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).setTitle(R.string.base_permissions_required).
                    setRationale(R.string.base_permissions_open_settings).build().show();
        }
    }
}
