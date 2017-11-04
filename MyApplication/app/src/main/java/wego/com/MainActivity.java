package wego.com;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import wego.com.common.BaseActivity;
import wego.com.hompage.HomePageFragment;
import wego.com.hompage.adapter.HomePageAdapter;
import wego.com.http.RetrofitService;
import wego.com.http.common.interz.OnNetRequestListener;
import wego.com.http.response.HttpResult;
import wego.com.mvp.presenter.TxtPricePresenter;
import wego.com.mvp.presenter.TxtPricePresenterImpl;
import wego.com.mvp.view.TxtPriceView;

public class MainActivity extends BaseActivity implements TxtPriceView{

    private Button mBtn;
    private TxtPricePresenter txtPricePresenter;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;
    @BindView(R.id.page_home)
    ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    private HomePageAdapter pageAdapter;

    @Override
    protected void setLayoutRes() {
        layoutResId=R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
//        txtPricePresenter=new TxtPricePresenterImpl(this);
        fragmentList=new ArrayList<>();
        for (int i = 0; i <4; i++) {
            HomePageFragment homePage=new HomePageFragment();
            fragmentList.add(homePage);
        }
        pageAdapter=new HomePageAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(4);
        alphaTabsIndicator.setViewPager(viewPager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mBtn=(Button) findViewById(R.id.click_btn);

//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                txtPricePresenter.getTxtData("493", new OnNetRequestListener() {
//                    @Override
//                    public void onStart() {
//                        Log.i("test","onStart()");
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        Log.i("test","onFinish()");
//
//                    }
//
//                    @Override
//                    public void onSuccess(Object data) {
//                        HttpResult httpResult = (HttpResult) data;
//                        Log.i("test","aaaa= "+httpResult.getCode()+"///"+"bbb= "+httpResult.getMessage()+"///"+"ccc= "+httpResult.getData());
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        Log.i("test","onFailure()");
//                    }
//                },0);
//            }
//        });
    }

    @Override
    public void showData(Object obj) {

    }



}
