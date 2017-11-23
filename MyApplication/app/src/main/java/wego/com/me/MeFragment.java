package wego.com.me;

import android.view.LayoutInflater;
import android.view.View;

import wego.com.R;
import wego.com.common.BaseFragment;

/**
 * Created by Administrator on 2017/11/11.
 */

public class MeFragment extends BaseFragment {

    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_me,null);
    }
}
