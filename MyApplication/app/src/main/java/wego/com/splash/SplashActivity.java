package wego.com.splash;

import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import wego.com.MainActivity;
import wego.com.R;
import wego.com.common.BaseActivity;
import wego.com.login.LoginActivity;

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
        myTimer=new MyTimer(5000,1000);
        myTimer.start();
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
            ToActivity(SplashActivity.this,MainActivity.class,null);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myTimer!=null){
            myTimer.cancel();
        }
    }
}
