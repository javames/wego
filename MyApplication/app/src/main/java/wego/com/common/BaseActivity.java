package wego.com.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wego.com.R;

/**
 * Created by Administrator on 2017/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected int layoutResId;
    protected Unbinder bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutRes();
        setContentView(layoutResId);
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected void initData(){}

    private void initView(){}
        


    public abstract void setLayoutRes();


    protected void Toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
    }
}
