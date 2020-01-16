package com.zf.base.net;

import com.zf.base.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitManager {

    private Retrofit mRetrofit;

    private static class InstanceHolder {
        private InstanceHolder() {
        }

        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    /**
     * 获取RetrofitManager单例
     *
     * @return 单例
     */
    public static RetrofitManager getInstance() {
        return InstanceHolder.INSTANCE;
    }


    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .client(OkHttpManager.getInstance().getClient())
                .baseUrl(BuildConfig.HOST_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    /**
     * @param service 服务接口
     * @return T
     */
    public <T> T createService(final Class<T> service) {
        return mRetrofit.create(service);
    }

}