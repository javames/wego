package wego.com.login;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import wego.com.R;
import wego.com.ResetApplication;
import wego.com.common.BaseActivity;
import wego.com.http.RetrofitService;
import wego.com.http.common.CommonApi;
import wego.com.http.response.HttpResult;
import wego.com.login.bean.RefreshTokenEntity;
import wego.com.util.JSONUtils;
import wego.com.util.PatternUtil;
import wego.com.util.ViewClickAnim;
import wego.com.widget.CustomToast;

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
    private EventHandler eh;
    private CountDownTimer cdt=new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long time = millisUntilFinished / 1000;
            getIdentityCode.setText(time+"("+"秒)后可重新获取");
            getIdentityCode.setTextSize(10);
        }

        @Override
        public void onFinish() {
            getIdentityCode.setText("获取验证码");
            getIdentityCode.setTextSize(14);
            identityNum.setClickable(true);
        }
    };
    @Override
    protected void setLayoutRes() {
        layoutResId = R.layout.activity_login;
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    //提交验证码成功
                    break;
                case 2:
                    //获取验证码成功
                    toast("获取验证码成功");
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
//                        HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                        Set<Map.Entry<String, Object>> entries = phoneMap.entrySet();
//                        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
//                        while (iterator.hasNext()){
//                           Map.Entry map= iterator.next();
//                            Object key = map.getKey();
//                            Object value = map.getValue();
//                            Log.i("test"," key= "+key+" value= "+value);
//                        }
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
        unDisposable();
        if(cdt!=null){
            cdt.cancel();
            cdt=null;
        }
    }

    @OnClick({R.id.identity_num, R.id.get_identity_code, R.id.login})
    public void onViewClicked(View view) {
        ViewClickAnim.clickAnim(view);
        switch (view.getId()) {
            case R.id.get_identity_code:
                String phone = phoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    toast("亲，你还没有填写手机号码哟");
                } else {
                    boolean phoneNumber = PatternUtil.isPhoneNumber(phone);
                    if (phoneNumber) {
                        SMSSDK.getVerificationCode("86", phone);
                        identityNum.setClickable(false);
                        cdt.start();
                    } else {
                        toast("手机号码格式不正确，请检查！");
                    }
                }

                break;
            case R.id.login:
                String phone1 = phoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(phone1)) {
                    toast("手机号码不能为空哦");
                } else {
                    boolean phoneNumber = PatternUtil.isPhoneNumber(phone1);
                    if (!phoneNumber) {
                        toast("手机号码格式不正确，请检查！");
                        return;
                    }
                    String code=identityNum.getText().toString().trim();

                    if(TextUtils.isEmpty(code)){
                        toast("验证码不能为空喔！");
                        return;
                    }else{
                        boolean digitalValue = PatternUtil.isDigitalValue(code);
                        if(!digitalValue){
                            toast("亲，验证码为数字");
                            return;
                        }else{
                            checkCode(phone1,code);
                        }
                    }

                }
                break;
        }
    }


    private void checkCode(String phone, String code) {

        Observable<HttpResult> check = RetrofitService.getInstance().createDuomiAPI().getIdentityCode(phone,code);
        check.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("test", "onSubscribe.....");
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(HttpResult httpResult) {

                        Log.i("test", "onNext....httpResult: "+httpResult.toString());
                        String resultCode = httpResult.getCode();
                        switch (resultCode){
                            case CommonApi.succedCode:
                                //登录成功
                                String jsonObj=httpResult.getData().toString();
                                RefreshTokenEntity refreshTokenEntity= (RefreshTokenEntity) JSONUtils.JSONToObj(jsonObj, RefreshTokenEntity.class);
                                //保存token到本地
                                ResetApplication.set(CommonApi.refreshTokenEntity,jsonObj);
                                finish();

                                break;
                            case CommonApi.errorCode:
                                toast("登录失败！");
                                break;
                            case CommonApi.register_fail:
                                toast("登录失败！");
                                break;
                            case CommonApi.identify_code_error:
                                //验证码错误
                                toast("验证码错误！");
                                break;
                            case CommonApi.identify_code_unused:
                                //验证码已经无效
                                toast("验证码已经无效！");
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("test", "onError....." + e.getMessage() + "// " + e.getCause()+" e= "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("test", "onCompleted.....");
                    }
                });



    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString;
            if(param == null){
                urlNameString = url;
            }else{
                urlNameString = url + "?" + param;
            }
            Log.i("请求地址：{}",urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
/*			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            Log.i("发送GET请求出现异常！{}",e.getMessage());
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }



}
