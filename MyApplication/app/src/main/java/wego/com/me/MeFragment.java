package wego.com.me;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.OnClick;
import wego.com.R;
import wego.com.common.BaseFragment;
import wego.com.login.LoginActivity;

/**
 * Created by Administrator on 2017/11/11.
 */

public class MeFragment extends BaseFragment {

    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_me,null);
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @OnClick({R.id.unLogin,R.id.mywallet})
    public void viewClick(View view){
        switch (view.getId()){
            case R.id.unLogin:
                Intent intent=new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mywallet:
                break;
        }
    }
}
