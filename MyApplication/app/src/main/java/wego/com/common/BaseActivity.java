package wego.com.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    protected AppCompatActivity activity;
    protected Bundle bundleExtra;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutRes();
        setWindowParams();
        setContentView(layoutResId);
        bind = ButterKnife.bind(this);
        activity=this;
        getIntentArgus();
        initView();
        initData();
    }

    private void getIntentArgus() {
        Intent intent = getIntent();
        if(intent.hasExtra("data")){
            bundleExtra = intent.getBundleExtra("data");
        }
    }

    protected void setWindowParams() {
    }

    protected void initData(){}

    protected void initView(){}


    protected void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    protected abstract void setLayoutRes();


    protected void Toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    protected void ToActivity(Context activity,Class activityClass,Bundle bundle){
        Intent intent=new Intent(activity,activityClass);
        if(bundle!=null){
            intent.putExtra("data",bundle);
        }
        startActivity(intent);
    }
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();
        }
    }
}
