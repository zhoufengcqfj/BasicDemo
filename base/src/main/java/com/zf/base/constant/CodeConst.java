package com.zf.base.constant;

/**
 * desc: 请求码&返回码常量 <br/>
 * time: 2018/3/27 <br/>
 * author: Vincent <br/>
 * since V1.0.0 <br/>
 */

public class CodeConst {

    //--------------------权限申请--------------------------//
    /**
     * 相机权限
     */
    public static final int PERMISSION_REQUEST_CAMERA = 0;

    /**
     * 存储权限
     */
    public static final int PERMISSION_REQUEST_STORAGE = 1;

    /**
     * 头像裁剪返回intent
     */
    public static final int PERMISSION_IMAGE_CROP = 2;

    //================= 页面 request code 100以下 ===================//
    /**
     * 个人信息页请求码
     */
    public static final int REQUEST_CODE_MY_INFO = 1;
    /**
     * 国家列表页请求码
     */
    public static final int REQUEST_CODE_COUNTRY_LIST = 2;
    /**
     * 国家列表页请求码
     */
    public static final int REQUEST_CODE_EIDT_MY_INFO = 3;
    /**
     * code:分享
     */
    public static final int REQUEST_CODE_SHARE = 4;

    /**
     * 登录 requestCode码
     */
    public static final int REQUEST_CODE_LOGIN_NORMAL = 5;

    /**
     * 搭配book弹框
     */
    public static final byte REQUEST_CODE_BOOK_INFO_LIST = 6;

    //================= 页面返回 result code 100+ ===================//
    /**
     * create book
     */
    public static final int ACTIVITY_CREATE_OR_EDIT_BOOK = 101;
    /**
     * search topicTag
     */
    public static final int ACTIVITY_SEARCH_TOPIC_TAG = 102;
    /**
     * 搭配book弹框返回
     */
    public static final byte RESPONSE_CODE_BOOK_INFO_LIST = 103;

    //============== 功能性级别 :3000 - 3999 ============== //
    /**
     * 裁剪图片
     */
    public static final int REQUEST_CODE_FOR_RESULT_CROP_IMAGE = 3005;
    /**
     * 拍照
     */
    public static final int REQUEST_CODE_FOR_RESULT_CAMERA = 3006;
    /**
     * 相册
     */
    public static final int REQUEST_CODE_FOR_RESULT_PHOTO = 3007;
}

