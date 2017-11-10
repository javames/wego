package wego.com.find.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import wego.com.R;
import wego.com.find.bean.FindBean;

/**
 * Created by Administrator on 2017/11/9.
 */

public class FindFragmentAdapter extends BaseQuickAdapter<FindBean,BaseViewHolder> {

    public FindFragmentAdapter(Context context, ArrayList data) {
        super(R.layout.find_dynamic_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindBean item) {

    }
}
