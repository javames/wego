package wego.com.recommend;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import wego.com.R;
import wego.com.common.BaseFragment;
import wego.com.interz.OnVerticalScrollListener;
import wego.com.recommend.adapter.RecomAdapter;
import wego.com.recommend.bean.RecomBean;
import wego.com.util.WindowUtil;
import wego.com.widget.SingleTabTiltle;

/**
 * Created by Administrator on 2017/11/10.
 */

public class RecomFragment extends BaseFragment {

    @BindView(R.id.single_tab)
    SingleTabTiltle tabTiltle;
    @BindView(R.id.recom_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recom_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.city_select_lay)
    RelativeLayout selectTab;
    @BindView(R.id.title)
    View title;

    private RecomAdapter recomAdapter;
    ArrayList<RecomBean> recomList;
    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_recom,null);
    }

    @Override
    protected void initView() {
        super.initView();
        int statusBarHeight = WindowUtil.getStatusBarHeight(mContext);
        title.setLayoutParams(new LinearLayout.LayoutParams(title.getMeasuredWidth(),statusBarHeight));
        tabTiltle.setTitle(getResources().getString(R.string.recom));
        tabTiltle.setTitleColor(getResources().getColor(R.color.white));

        recomList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            recomList.add(new RecomBean());
        }
        recomAdapter=new RecomAdapter(mContext,recomList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recomAdapter);

        recyclerView.addOnScrollListener(new OnVerticalScrollListener(){


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("test"," dx= "+dx+" dy= "+dy);
//
//                if (dy > duration) {
//                    scrollY = duration;
//                }
//
//                float v1 = scrollY / duration * 1f;
//
//                toolbar.setAlpha(1-v1);
//                searchToolbar.setAlpha(v1);
//                if(v1==1){
//                    toolbarRt .setBackgroundColor(getResources().getColor(R.color.transparent));
//                }
//                toolbarRt.setAlpha(1-v1);
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                Log.i("test","onScrolledUp");
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                selectTab.setVisibility(View.VISIBLE);
                Log.i("test","onScrolledDown");

            }

            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                selectTab.setVisibility(View.GONE);
                Log.i("test","onScrolledToTop");

            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
            }
        });
    }
}
