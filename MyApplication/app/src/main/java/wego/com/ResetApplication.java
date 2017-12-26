package wego.com;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;

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

        MobSDK.init(this,"234f1eee14d17","37e06cdf17f064d27547a08f16d0cc1d");

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext(){
        return mContext;
    }
}
