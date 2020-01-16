package com.zf.base.mvp.presenter;


import com.zf.base.mvp.view.BaseView;

/**
 * Presenter 基类
 *
 */
public abstract class BasePresenter<V extends BaseView>  {

    protected V mView;

    public BasePresenter(V view) {
        this.mView = view;
    }

}
