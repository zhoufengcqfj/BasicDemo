package com.zf.base.tool;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zf.base.R;


/**
 * desc: 自定义Toast提示框 <br/>
 */
public class ToolToast {

    private static Context context;


	private static Toast toast;


	private ToolToast() {

	}

	public static void setContext(Context context1){
	    context = context1;
    }

	/**
	 * 显示toast
	 */
	public static void showToast(String msg) {
		showToast(msg, Toast.LENGTH_SHORT);
	}

	/**
	 * 显示toast
	 */
	public static void showToast(int resId) {
		showToast(resId, Toast.LENGTH_SHORT);
	}

	/**
	 * 显示toast
	 *
	 * @param resIdOrMsg 字符串资源id或者显示文案字符串
	 * @param duration   显示时长，值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
	 */
	public static void showToast(Object resIdOrMsg, int duration) {
		String msg = null;

		if (resIdOrMsg instanceof Integer) {
			if (context.getResources() == null) {
				return;
			}
			msg = context.getResources().getString((Integer) resIdOrMsg);
		} else if (resIdOrMsg instanceof String) {
			msg = (String) resIdOrMsg;
		}

		showToast(msg, duration, Gravity.CENTER, 0, 100);
	}

	/**
	 * 显示toast(位置自定义)
	 *
	 * @param resId    字符串资源id
	 * @param duration 值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
	 * @param gravity  文案排版方向，值如：{@link Gravity#CENTER}等
	 * @param xOffset  x轴偏移量
	 * @param yOffset  y轴偏移量
	 */
	public static void showToast(int resId, int duration, int gravity, int xOffset, int yOffset) {
		if (context.getResources() != null) {
			showToast(context.getResources().getString(resId), duration, gravity, xOffset, yOffset);
		}
	}

	/**
	 * 显示toast
	 *
	 * @param msg      显示内容
	 * @param duration 显示时长，值为：{@link Toast#LENGTH_SHORT}或 {@link Toast#LENGTH_LONG}
	 * @param gravity  文案排版方向
	 * @param xOffset  x轴偏移量
	 * @param yOffset  y轴偏移量
	 */
	@SuppressLint("InflateParams")
	private static void showToast(String msg, int duration, int gravity, int xOffset, int yOffset) {
		if (TextUtils.isEmpty(msg)) {
			return;
		}

		if (toast == null) {
			View view = LayoutInflater.from(context).inflate(R.layout.base_view_toast_custom, null);
			Drawable drawable = ContextCompat.getDrawable(context, R.drawable.base_bg_toast_custom);

			// 设置透明度为70%
			drawable.setAlpha((int) (255 * 0.7));
			view.setBackground(drawable);
			TextView text = view.findViewById(R.id.tv_msg);

			if (text == null) {
				return;
			}

			text.setText(msg);
			toast = new Toast(context);
			toast.setView(view);
		} else {
			View view = toast.getView();
			TextView tv = view.findViewById(R.id.tv_msg);
			tv.setText(msg);
		}

		toast.setDuration(duration);
		toast.setGravity(gravity, xOffset, yOffset);
		toast.show();
	}

}
