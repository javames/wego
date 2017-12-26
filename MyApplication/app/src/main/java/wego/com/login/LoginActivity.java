package wego.com.login;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import wego.com.R;
import wego.com.common.BaseActivity;
import wego.com.util.PatternUtil;

/**
 * Created by Administrator on 2017/10/28.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.identity_num)
    EditText identityNum;
    @BindView(R.id.get_identity_code)
    TextView getIdentityCode;
    @BindView(R.id.login)
    TextView login;
    EventHandler eh;
    @Override
    protected void setLayoutRes() {
        layoutResId = R.layout.activity_login;
    }

    private Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    //提交验证码成功
                    break;
                case 2:
                    //获取验证码成功
                    break;
                case 3:
                    //获取验证码失败，请查看手机号码
                    break;
                case 4:
                    //验证码验证失败
                    break;
                case 5:
                    break;

            }
        }
    };


    @Override
    protected void initView() {
        super.initView();

        eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        mHandler.sendEmptyMessage(1);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                        mHandler.sendEmptyMessage(2);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    @OnClick({R.id.identity_num, R.id.get_identity_code, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_identity_code:
                String phone=phoneNum.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    toast("亲，你还没有填写手机号码哟");
                }else{
                    boolean phoneNumber = PatternUtil.isPhoneNumber(phone);
                    if(phoneNumber){
                        SMSSDK.getVerificationCode("86", phone);
                    }else {
                        toast("手机号码格式不正确，请检查！");
                    }
                }

                break;
            case R.id.login:
                break;
        }
    }
}
