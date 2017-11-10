package wego.com.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wego.com.R;

/**
 * Created by Administrator on 2017/11/9.
 */

public abstract class BaseFragment extends BaseLazyFragment {

    private Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=setContentView(inflater);
        bind = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    protected abstract View setContentView(LayoutInflater inflater);

    protected void initView(){}

    protected void initData(){}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
