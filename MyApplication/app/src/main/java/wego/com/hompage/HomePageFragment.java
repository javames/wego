package wego.com.hompage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

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

    private HomePagerDynamicAdapter adapter;

    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    protected void initView() {
        super.initView();


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
    }

}
