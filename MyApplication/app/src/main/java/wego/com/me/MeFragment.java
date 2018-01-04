package wego.com.me;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import wego.com.R;
import wego.com.ResetApplication;
import wego.com.common.BaseFragment;
import wego.com.event.UserInfoEvent;
import wego.com.http.common.CommonApi;
import wego.com.login.LoginActivity;
import wego.com.login.bean.RefreshTokenEntity;
import wego.com.util.JSONUtils;

/**
 * Created by Administrator on 2017/11/11.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.unLogin)
    TextView unLogin;
    @BindView(R.id.login_in)
    LinearLayout loginIn;
    @BindView(R.id.nickname)
    TextView nickName;

    private Dialog selectIconDialog;

    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_me,null);
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);


    }

    @OnClick({R.id.unLogin,R.id.mywallet,R.id.head_icon})
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.unLogin:
                Intent intent=new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mywallet:
                break;
            case R.id.head_icon:
//                openIconDialog();
                toActivity(mContext,AvatorActivity.class);
                break;
        }
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReciveInfo(UserInfoEvent userInfoEvent){
        unLogin.setVisibility(View.GONE);
        loginIn.setVisibility(View.VISIBLE);
        nickName.setText(userInfoEvent.getName());
    }


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        String jsonString= ResetApplication.get(CommonApi.refreshTokenEntity,null);
        if(!TextUtils.isEmpty(jsonString)){
            RefreshTokenEntity refreshTokenEntity= (RefreshTokenEntity) JSONUtils.JSONToObj(jsonString, RefreshTokenEntity.class);
            if(refreshTokenEntity!=null){
                unLogin.setVisibility(View.GONE);
                loginIn.setVisibility(View.VISIBLE);
                nickName.setText(refreshTokenEntity.getName());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
