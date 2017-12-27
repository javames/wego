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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import wego.com.R;
import wego.com.widget.CustomToast;

/**
 * Created by Administrator on 2017/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected int layoutResId;
    protected Unbinder bind;
    protected AppCompatActivity activity;
    protected Bundle bundleExtra;
    protected CompositeDisposable compositeDisposable;
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
//        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        CustomToast.Companion.showToast(this,msg);
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

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    protected void addDisposable(Disposable subscription) {
        if(compositeDisposable==null||compositeDisposable.isDisposed()){
            compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(subscription);
        }
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    protected void unDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
