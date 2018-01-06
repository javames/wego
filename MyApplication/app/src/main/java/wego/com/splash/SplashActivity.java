package wego.com.splash;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import wego.com.MainActivity;
import wego.com.R;
import wego.com.ResetApplication;
import wego.com.common.BaseActivity;
import wego.com.event.UserInfoEvent;
import wego.com.http.RetrofitService;
import wego.com.http.common.CommonApi;
import wego.com.http.response.HttpResult;
import wego.com.login.LoginActivity;
import wego.com.login.bean.RefreshTokenEntity;
import wego.com.util.Constants;
import wego.com.util.JSONUtils;
import wego.com.util.WindowUtil;

/**
 * Created by Administrator on 2017/10/28.
 */

public class SplashActivity extends BaseActivity{


    @BindView(R.id.timer_text)
    TextView timerText;
    private MyTimer myTimer;

    @Override
    public void setLayoutRes() {
        layoutResId= R.layout.splash;
    }

    @Override
    protected void setWindowParams() {
        super.setWindowParams();
    }

    @Override
    protected void initView() {
        super.initView();
        Window window = this.getWindow();
        View decorView = window.getDecorView();
        decorView.setBackgroundResource(R.color.white);
        WindowUtil.fullScreen(activity);

        myTimer=new MyTimer(5000,1000);
        myTimer.start();

    }

    private void secondLogin(){
        String jsonString=ResetApplication.get(CommonApi.refreshTokenEntity,null);
        Log.i(TAG," jsonString: "+jsonString);
        if(!TextUtils.isEmpty(jsonString)){
            RefreshTokenEntity refreshTokenEntity= (RefreshTokenEntity) JSONUtils.JSONToObj(jsonString,RefreshTokenEntity.class);
            Observable<HttpResult<RefreshTokenEntity>> secondLogin = RetrofitService.getInstance().createDuomiAPI().secondLogin(refreshTokenEntity);
            secondLogin.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<HttpResult<RefreshTokenEntity>>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.i(TAG, "onSubscribe.....");
                            addDisposable(d);
                        }

                        @Override
                        public void onNext(HttpResult<RefreshTokenEntity> httpResult) {

                            Log.i(TAG, "onNext....httpResult: "+httpResult.toString());
                            String resultCode = httpResult.getCode();
                            switch (resultCode){
                                case CommonApi.succedCode:
                                    //登录成功
                                    RefreshTokenEntity refreshTokenEntity=httpResult.getData();
                                    String jsonObj=JSONUtils.GsonString(refreshTokenEntity);
                                    //保存token到本地
                                    ResetApplication.set(CommonApi.refreshTokenEntity,jsonObj);
                                    UserInfoEvent userInfoEvent=new UserInfoEvent(refreshTokenEntity.getName(),refreshTokenEntity.getHeadImage());
                                    EventBus.getDefault().post(userInfoEvent);
                                    ToActivity(SplashActivity.this,MainActivity.class,null);
                                    break;
                                case CommonApi.outUsedTime:
                                    toast("超出时间失效！");
                                    ResetApplication.set(CommonApi.refreshTokenEntity,null);
                                    ToActivity(SplashActivity.this,LoginActivity.class,null);
                                    break;
                                case CommonApi.userNotExsit:
                                    toast("用户不存在！");
                                    ToActivity(SplashActivity.this,LoginActivity.class,null);
                                    break;
                            }
                            finish();

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "onError....." + e.getMessage() + "// " + e.getCause()+" e= "+e);
                            ToActivity(SplashActivity.this,LoginActivity.class,null);
                        }

                        @Override
                        public void onComplete() {
                            Log.i("test", "onCompleted.....");
                        }
                    });
        }else {
            finish();
            ToActivity(this,LoginActivity.class,null);
        }
    }
    private class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            if(timerText!=null){
                timerText.setText(String.valueOf(l/1000));
            }
        }

        @Override
        public void onFinish() {
            if(timerText!=null){
                timerText.setText("0");
            }

            secondLogin();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myTimer!=null){
            myTimer.cancel();
        }
        unDisposable();
    }
}
