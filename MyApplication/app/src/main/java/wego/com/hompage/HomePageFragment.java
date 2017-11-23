package wego.com.hompage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wego.com.R;
import wego.com.common.BaseFragment;
import wego.com.common.BaseLazyFragment;
import wego.com.hompage.adapter.HomePagerDynamicAdapter;
import wego.com.hompage.bean.HPageDynamicBean;
import wego.com.http.common.CommonApi;
import wego.com.interz.OnVerticalScrollListener;
import wego.com.util.GlideImageLoader;
import wego.com.util.ViewClickAnim;

/**
 * Created by Administrator on 2017/10/31.
 */

public class HomePageFragment extends BaseFragment {

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.city_select_icon)
    ImageView citySelectIcon;
    @BindView(R.id.msg_btn)
    ImageView msgBtn;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    private float distanceY;
    private HomePagerDynamicAdapter adapter;
    private float offset=128;
    private boolean isExpand;
    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void initView() {
        super.initView();
        citySelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExpand = isExpand == true ? false : true;
                citySelectIcon.setAlpha(0f);
                changeSelectIcon(citySelectIcon);
            }
        });

        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewClickAnim.clickAnim(view);
            }
        });

        ArrayList<HPageDynamicBean> list=new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            if(i==0){
                list.add(new HPageDynamicBean(2));
            }else{
                list.add(new HPageDynamicBean(3));
            }

        }
        adapter=new HomePagerDynamicAdapter(mContext,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
//        adapter.setNewData(list);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                },1000);
            }
        });

        recyclerView.addOnScrollListener(new OnVerticalScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("test"," dx= "+dx+" dy= "+dy);
                distanceY+=dy;
                Log.i("test"," distanceY= "+distanceY);
                float rate=0;
                if(distanceY>80){
                    rate=(distanceY-80)/offset;
                }
                searchLayout.setAlpha(1-rate);
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                Log.i("test"," onScrolledUp");
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                Log.i("test"," onScrolledUp");
            }

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                Log.i("test"," onScrolledUp");
            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                Log.i("test"," onScrolledUp");
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toActivity(mContext,HPageDtlActivity.class);
            }
        });
    }

    private void changeSelectIcon(final ImageView citySelectIcon) {
        if (isExpand) {
            citySelectIcon.setImageResource(R.mipmap.shousuo);
        } else {
            citySelectIcon.setImageResource(R.mipmap.zhankai);
        }
        citySelectIcon.postDelayed(new Runnable() {
            @Override
            public void run() {
                citySelectIcon.setAlpha(1f);
            }
        }, 100);

    }
}
