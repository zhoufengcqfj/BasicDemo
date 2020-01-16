package com.zf.base.observer;

import com.zf.base.net.exception.NetException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof NetException.ResponseException) {
            error((NetException.ResponseException) e);
        } else {
            onError(new NetException.ResponseException(e, NetException.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void success(T t);

    public abstract void error(NetException.ResponseException e);

}
