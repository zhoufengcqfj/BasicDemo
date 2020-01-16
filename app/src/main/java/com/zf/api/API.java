package com.zf.api;


import com.zf.data.bean.PublicAccountBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface API {
    /**
     * 获取公众号列表
     * @return  Observable<PublicAccountBean>
     */
    @POST("wxarticle/chapters/json")
    @FormUrlEncoded
    Observable<PublicAccountBean> getPublicAccountList(@FieldMap Map<String, String> map);

}
