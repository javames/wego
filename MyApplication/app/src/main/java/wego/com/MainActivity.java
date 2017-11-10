package wego.com;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yinglan.alphatabs.AlphaTabsIndicator;
import com.yinglan.alphatabs.OnTabChangedListner;

import java.util.ArrayList;

import butterknife.BindView;
import wego.com.common.BaseActivity;
import wego.com.find.FindFragment;
import wego.com.hompage.HomePageFragment;
import wego.com.hompage.adapter.HomePageAdapter;
import wego.com.mvp.presenter.TxtPricePresenter;
import wego.com.mvp.view.TxtPriceView;
import wego.com.util.WindowUtil;
import wego.com.widget.NoScrollViewPager;

public class MainActivity extends BaseActivity implements TxtPriceView{

    private Button mBtn;
    private TxtPricePresenter txtPricePresenter;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;
    @BindView(R.id.page_home)
    NoScrollViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    private HomePageAdapter pageAdapter;

    @Override
    protected void setLayoutRes() {
        layoutResId=R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        WindowUtil.fullScreen(this);
        fragmentList=new ArrayList<>();
        for (int i = 0; i <4; i++) {
            if(i==1){
                FindFragment findFragment=new FindFragment();
                fragmentList.add(findFragment);
            }else{
                HomePageFragment homePage=new HomePageFragment();
                fragmentList.add(homePage);
            }
        }
        pageAdapter=new HomePageAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(4);
        alphaTabsIndicator.setViewPager(viewPager);

        alphaTabsIndicator.setTabCurrenItem(0);

        alphaTabsIndicator.setOnTabChangedListner(new OnTabChangedListner() {
            @Override
            public void onTabSelected(int tabNum) {
                Log.i("test"," tabNum= "+tabNum);
                switch (tabNum){
                    case 0:
                        setRootMargin(false);
                        WindowUtil.fullScreen(activity);
                        break;
                    case 1:
                        setRootMargin(true);
                        WindowUtil.setStatusBarColor(activity,R.color.main_blue);
                        break;
                    case 2:
                        setRootMargin(true);
                        WindowUtil.setStatusBarColor(activity,R.color.main_blue);
                        break;
                    case 3:
                        setRootMargin(true);
                        WindowUtil.setStatusBarColor(activity,R.color.main_blue);
                        break;
                }
            }
        });
    }

    /**
     * 解决透明状态栏下，布局无法自动拉起的问题
     * 手动设置View的高度
     */
    private void setRootMargin(final boolean flag) {
        final View rootView = ((ViewGroup) this.findViewById(android.R.id.content))
                .getChildAt(0);
        final View decorView = getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                Rect rect= new Rect();
                decorView.getWindowVisibleDisplayFrame(rect);
                int screenHeight = decorView.getRootView().getHeight();
                int heightDifferent = screenHeight - rect.bottom;
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rootView.getLayoutParams();
                if(flag){
                    lp.topMargin=WindowUtil.getStatusBarHeight(MainActivity.this);
                }else{
                    lp.topMargin=0;
                }

//                lp.setMargins(0, 0, 0, heightDifferent);
                rootView.requestLayout();
            }
        });

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


    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }


}
