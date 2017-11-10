package wego.com.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wego.com.R;
import wego.com.common.BaseFragment;
import wego.com.common.BaseLazyFragment;
import wego.com.find.adapter.FindFragmentAdapter;
import wego.com.find.bean.FindBean;
import wego.com.widget.SingleTabTiltle;

/**
 * Created by Administrator on 2017/11/8.
 */

public class FindFragment extends BaseFragment {

    @BindView(R.id.find_title)
    SingleTabTiltle singleTab;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private ArrayList<FindBean> findBeanList;
    private FindFragmentAdapter adapter;

    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_find,null);
    }

    @Override
    protected void initView() {
        super.initView();
        singleTab.setTitle(getResources().getString(R.string.find));
        singleTab.setTitleColor(getResources().getColor(R.color.white));
        findBeanList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            findBeanList.add(new FindBean());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter=new FindFragmentAdapter(mContext,findBeanList);
        recyclerView.setAdapter(adapter);
    }
}
