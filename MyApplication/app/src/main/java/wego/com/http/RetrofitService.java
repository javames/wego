package wego.com.http;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import wego.com.ResetApplication;
import wego.com.http.response.JSONConverterFactory;
import wego.com.http.util.NetUtil;
import wego.com.httpApi.DuomiApi;

/**
 * 单例Retrofit管理类
 * Created by Administrator on 2017/10/26.
 */

public class RetrofitService {

    //设缓存有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private static OkHttpClient mOkHttpClient;

    private static final String BASE_URL="http://app.duomiwan.com/";

    private static class SingletonHolder {
        private static final RetrofitService INSTANCE = new RetrofitService();
    }
    private RetrofitService (){}
    public static final RetrofitService getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 多米玩api
     */
    private volatile static DuomiApi duomiAPI = null;
    public static DuomiApi createDuomiAPI() {
        if (duomiAPI == null) {
            synchronized (RetrofitService.class) {
                if (duomiAPI == null) {
                    initOkHttpClient();
                    duomiAPI = new Retrofit.Builder()
                            .client(mOkHttpClient)
                            .baseUrl(BASE_URL)
                            .addConverterFactory(JSONConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build().create(DuomiApi.class);
                }
            }
        }
        return duomiAPI;
    }

    // 配置OkHttpClient
    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
            File cacheFile = new File(ResetApplication.getContext().getCacheDir(),
                    "HttpCache"); // 指定缓存路径
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
            // 云端响应头拦截器，用来配置缓存策略
            Interceptor rewriteCacheControlInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!NetUtil.isConnected(ResetApplication.getContext())) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE).build();

                    }
                    Response originalResponse = chain.proceed(request);
                    if (NetUtil.isConnected(ResetApplication.getContext())) {
                        //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                        String cacheControl = request.cacheControl().toString();
                        return originalResponse.newBuilder()
                                .header("Cache-Control", cacheControl)
                                .removeHeader("Pragma").build();
                    } else {
                        return originalResponse.newBuilder().header("Cache-Control",
                                "public, only-if-cached," + CACHE_STALE_SEC)
                                .removeHeader("Pragma").build();
                    }
                }
            };
//            mOkHttpClient = new OkHttpClient();
//            mOkHttpClient.setCache(cache);
//            mOkHttpClient.networkInterceptors().add(rewriteCacheControlInterceptor);
//            mOkHttpClient.interceptors().add(rewriteCacheControlInterceptor);
//            mOkHttpClient.connectTimeoutMillis();
            //okhttp 3
                    mOkHttpClient = new OkHttpClient.Builder().cache(cache)
                            .addNetworkInterceptor(rewriteCacheControlInterceptor)
                            .addInterceptor(rewriteCacheControlInterceptor)
                            .connectTimeout(30, TimeUnit.SECONDS).build();

        }
    }

    public static String getCacheControl() {
        return NetUtil.isConnected(ResetApplication.getContext()) ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }


    private static ArrayMap<String, CompositeDisposable> netManager = new ArrayMap<>();

    //为了避免错误的取消了，key建议使用packagename + calssName
    public static void add(String key, Disposable disposable) {
        if (netManager.containsKey(key)) {
            netManager.get(key).add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            netManager.put(key, compositeDisposable);
        }
    }

    public static void remove(String key) {
        if (netManager.containsKey(key)) {
            CompositeDisposable compositeDisposable = netManager.get(key);
            compositeDisposable.clear();
        }
    }



}
