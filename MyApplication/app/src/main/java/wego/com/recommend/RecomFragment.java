package wego.com.recommend;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import wego.com.R;
import wego.com.common.BaseFragment;
import wego.com.recommend.adapter.RecomAdapter;
import wego.com.recommend.bean.RecomBean;
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
    private RecomAdapter recomAdapter;
    ArrayList<RecomBean> recomList;
    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_recom,null);
    }

    @Override
    protected void initView() {
        super.initView();
        tabTiltle.setTitle(getResources().getString(R.string.recom));
        tabTiltle.setTitleColor(getResources().getColor(R.color.white));

        recomList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            recomList.add(new RecomBean());
        }
        recomAdapter=new RecomAdapter(mContext,recomList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recomAdapter);
    }
}
