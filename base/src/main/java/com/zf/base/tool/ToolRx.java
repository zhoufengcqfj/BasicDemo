package com.zf.base.tool;

import android.support.annotation.IntRange;

import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zf.base.mvp.view.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * desc: rx工具类 <br/>
 * time: 2017/10/24 19:00 <br/>
 * author: Vincent <br/>
 * since V1.0 <br/>
 */
public class ToolRx {

    /**
     * 获取通用的转换器
     */
    public static <T> ObservableTransformer<T, T> getGeneralComposer() {
        return ioTransformer;
    }

    private static final ObservableTransformer ioTransformer = upstream -> upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    /**
     * 对数据流做转化
     *
     * @param activity
     * @param <T>
     * @return 返回处理好的Observable
     */
    public static <T> ObservableTransformer<T, T> getComposer(
            final BaseActivity activity) {
        return upstream -> upstream
                .compose(getGeneralComposer())
                .compose(RxLifecycle.bindUntilEvent(activity.getLifeCycleSubject(), ActivityEvent.DESTROY));
//                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY));
    }

    /**
     * 对数据流做转化
     *
     * @param fragment
     * @param <T>
     * @return 返回处理好的Observable
     */
//    public static <T> ObservableTransformer<T, T> getComposer(
//            final FragmentRx fragment) {
//        return upstream -> upstream
//                .compose(getGeneralComposer())
//                .compose(fragment.bindUntilEvent(FragmentEvent.DESTROY_VIEW));
//    }

    /**
     * 对数据流做转化
     *
     * @param fragment
     * @param <T>
     * @return 返回处理好的Observable
     */
//    public static <T> ObservableTransformer<T, T> getComposer(
//            final DialogFragmentRx fragment) {
//        return upstream -> upstream
//                .compose(getGeneralComposer())
//                .compose(fragment.bindUntilEvent(FragmentEvent.DESTROY));
//    }

    /**
     * 获取一个用作倒计时的Observable
     *
     * @param seconds 倒计时的秒数
     */
    public static Observable<Integer> getCountdownObservable(
            @IntRange(from = 1, to = Integer.MAX_VALUE) int seconds) {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> seconds - aLong.intValue() - 1)
                .takeUntil(integer -> integer == 0);
    }
}