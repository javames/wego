package wego.com.find;

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
 * Created by Administrator on 2017/11/8.
 */

public class FindFragment extends BaseLazyFragment {

    private Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_find,container,false);
        bind = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){

    }
}
