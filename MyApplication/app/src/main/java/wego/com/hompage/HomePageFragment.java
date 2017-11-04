package wego.com.hompage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wego.com.R;
import wego.com.common.BaseLazyFragment;

/**
 * Created by Administrator on 2017/10/31.
 */

public class HomePageFragment extends BaseLazyFragment {

    private Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        bind = ButterKnife.bind(this, view);
        initView();
        return view;

    }

    private void initView(){

    }
}
