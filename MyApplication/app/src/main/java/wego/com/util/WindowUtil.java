package wego.com.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.security.AllPermission;

/**
 * Created by Administrator on 2017/10/31.
 */

public class WindowUtil {

    /**
     * 隐藏虚拟建，触屏也不会显示出来
     * @param activity
     */
    public static void setScreenAllFull(AppCompatActivity activity){
        Window _window = activity.getWindow();
        WindowManager.LayoutParams params = _window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
        _window.setAttributes(params);
    }
    /**
     * 隐藏虚拟建，触屏会显示出来
     * @param activity
     */
    public static void setScreenAutoFull(AppCompatActivity activity){
        Window _window = activity.getWindow();
        WindowManager.LayoutParams params = _window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        _window.setAttributes(params);
    }


}
