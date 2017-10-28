package wego.com;

import android.app.Application;
import android.content.Context;

import wego.com.util.CrashUtil;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ResetApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        CrashUtil.getInstance().init(this);
    }

    public static Context getContext(){
        return mContext;
    }
}
