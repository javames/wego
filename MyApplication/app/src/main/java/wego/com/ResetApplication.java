package wego.com;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v4.content.SharedPreferencesCompat;

import com.mob.MobSDK;
import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import wego.com.util.CrashUtil;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ResetApplication extends Application {

    private static final String PREF_NAME = "wego.pref";
    private static Context mContext;
    private static UploadManager uploadManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        CrashUtil.getInstance().init(this);

        MobSDK.init(this,"234f1eee14d17","37e06cdf17f064d27547a08f16d0cc1d");

        uploadManager = new UploadManager();
    }


    public static SharedPreferences getPreferences() {
        return getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext(){
        return mContext;
    }

    public static UploadManager getUploadManager(){
        return uploadManager;
    }
}
