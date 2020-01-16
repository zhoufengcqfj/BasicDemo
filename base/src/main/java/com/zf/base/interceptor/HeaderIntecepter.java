package com.zf.base.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhoufeng on 2020/1/16.
 */

public class HeaderIntecepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

            // l headers
            Request newRequest = chain.request().newBuilder()
                        //todo 添加请求头相关信息
//                    .addHeader("User-Agent", CommonUtils.filterIllegalChar(RequestHelper.getUserAgent()))
//
//                    .addHeader("sign", RequestHelper.getSign())
//
//                    .addHeader("pid", DeviceInfo.getChannelId())
//
//                    .addHeader("token", UserManager.getToken(LvWanApp.getGlobContext()))
//
//                    .addHeader("uid", UserManager.getUid(LvWanApp.getGlobContext()))
//
//                    .addHeader("udid", CommonUtils.filterIllegalChar(UserManager.getUDid(LvWanApp.getGlobContext())))
//
//                    .addHeader("nettype", DeviceInfo.getNetWorkType())

                    .build();
            // pass chain
            return chain.proceed(newRequest);

    }
}
