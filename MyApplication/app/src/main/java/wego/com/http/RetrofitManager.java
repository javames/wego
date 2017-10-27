package wego.com.http;

/**
 * 单例Retrofit管理类
 * Created by Administrator on 2017/10/26.
 */

public class RetrofitManager {

    private volatile static RetrofitManager retrofitManager;


    private RetrofitManager(){}

    public RetrofitManager getRetrofitInstance(){
        if(retrofitManager==null){
            synchronized (RetrofitManager.class){
                if(retrofitManager!=null){
                    retrofitManager=new RetrofitManager();
                }
            }
        }
        return  retrofitManager;
    }




}
