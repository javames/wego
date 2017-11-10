package wego.com.recommend.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import wego.com.R;
import wego.com.find.bean.FindBean;
import wego.com.recommend.bean.RecomBean;

/**
 * Created by Administrator on 2017/11/10.
 */

public class RecomAdapter extends BaseQuickAdapter<RecomBean,BaseViewHolder> {

    public RecomAdapter(Context context, ArrayList data) {
        super(R.layout.recom_dynamic_item,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, RecomBean item) {

    }
}
