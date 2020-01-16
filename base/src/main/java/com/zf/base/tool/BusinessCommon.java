package com.zf.base.tool;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;


import com.zf.base.R;
import com.zf.base.constant.CodeConst;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * desc: 业务公共类(PS:不好分类的业务函数可先放在这里) <br/>
 * time: 2018/3/26 <br/>
 * author: Vincent <br/>
 * since V1.0.0 <br/>
 */

public class BusinessCommon {

    /**
     * 收藏：商品iD前加前缀控制包含的关系
     */
    public static final String PREFIX = "!";
    /**
     * 收藏：商品iD前加前缀控制包含的关系
     */
    public static final String SUFFIX = "#";

    /**
     * 打开相机的intent
     *
     * @param uri 输出到的文件路径 Uri.fromFile()
     */
    private static void jumpToSystemCamera(Fragment fragment, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        fragment.startActivityForResult(intent, CodeConst.REQUEST_CODE_FOR_RESULT_CAMERA);
    }

    /**
     * @see BusinessCommon#jumpToSystemCamera(Fragment, Uri)
     */
    private static void jumpToSystemCamera(Activity activity, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, CodeConst.REQUEST_CODE_FOR_RESULT_CAMERA);
    }

    /**
     * 打开相册的intent
     */
    private static void jumpToPhotoAlbum(Fragment fragment) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        fragment.startActivityForResult(intent, CodeConst.REQUEST_CODE_FOR_RESULT_PHOTO);
    }


    /**
     * 跳转系统相机，做了权限判断
     *
     * @param context  context
     * @param activity activity
     * @param uri      指定照片存储路径的uri
     */
    public static void jumpToSystemCamera(Context context, Activity activity, Uri uri) {
        if (EasyPermissions.hasPermissions(context, Manifest.permission.CAMERA)) {
            jumpToSystemCamera(activity, uri);
        } else {
            EasyPermissions.requestPermissions(activity, context.getString(R.string.base_rationale_ask_camera_permission), R.string.base_ok, R.string.base_cancel, CodeConst.PERMISSION_REQUEST_CAMERA, Manifest.permission.CAMERA);
        }
    }

    /**
     * @see BusinessCommon#jumpToSystemCamera(Context, Activity, Uri)
     */
    public static void jumpToSystemCamera(Context context, Fragment fragment, Uri uri) {
        if (EasyPermissions.hasPermissions(context, Manifest.permission.CAMERA)) {
            jumpToSystemCamera(fragment, uri);
        } else {
            EasyPermissions.requestPermissions(fragment, context.getString(R.string.base_rationale_ask_camera_permission), R.string.base_ok, R.string.base_cancel, CodeConst.PERMISSION_REQUEST_CAMERA, Manifest.permission.CAMERA);
        }
    }

    /**
     * 跳转系统相册，做了权限判断
     *
     * @param context  context
     * @param fragment fragment
     */
    public static void jumpToPhotoAlbum(Context context, Fragment fragment) {
        if (EasyPermissions.hasPermissions(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            jumpToPhotoAlbum(fragment);
        } else {
            EasyPermissions.requestPermissions(fragment, context.getString(R.string.base_rationale_ask_storage_permission), R.string.base_ok, R.string.base_cancel, CodeConst.PERMISSION_REQUEST_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }
    /**
     * 跳转系统相册，做了权限判断
     *
     * @param activity activity
     */
    public static void jumpToPhotoAlbum(Activity activity) {
        if (EasyPermissions.hasPermissions(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            jumpToPhotoAlbumWhithPermissions(activity);
        } else {
            EasyPermissions.requestPermissions(activity, activity.getString(R.string.base_rationale_ask_storage_permission), R.string.base_ok, R.string.base_cancel, CodeConst.PERMISSION_REQUEST_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    /**
     * 有权限跳转相册
     *
     * @param activity
     */
    private static void jumpToPhotoAlbumWhithPermissions(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, CodeConst.REQUEST_CODE_FOR_RESULT_PHOTO);
    }

    /**
     * 判断是否存在某程序
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean ifHavePackage(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        //获取手机系统的所有APP包名，然后进行一一比较
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 新拼接方式(V mv4.0)
     *
     * @param imageUrl 图片全路径
     * @param imgSize  图片所需展示的大小以及质量
     * @return 拼接后地址
     */
//    public static String fixImageUrl(String imageUrl, String imgSize) {
//        if (TextUtils.isEmpty(imageUrl)) {
//            return "";
//        }
//        String fixImgStr = imageUrl + "_" + imgSize;
//        return ToolApp.isKitKatOrLater() ? fixImgStr + ".webp" : fixImgStr + ".jpg";
//    }


}
