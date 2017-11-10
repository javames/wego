package wego.com.hompage.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import wego.com.R;
import wego.com.hompage.bean.HPageDynamicBean;
import wego.com.http.common.CommonApi;
import wego.com.util.GlideImageLoader;
import wego.com.util.ViewClickAnim;

/**
 * Created by Administrator on 2017/11/9.
 */

public class HomePagerDynamicAdapter extends BaseMultiItemQuickAdapter<HPageDynamicBean,BaseViewHolder> {


    private ArrayList<String> bannerList;
    private boolean isExpand;

    public HomePagerDynamicAdapter(Context context, ArrayList data) {
        super(data);
        addItemType(HPageDynamicBean.IMG, R.layout.head_banner_item);
        addItemType(HPageDynamicBean.IMG_TEXT, R.layout.homepage_dynamic_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, HPageDynamicBean item) {
        switch (helper.getItemViewType()) {
            case HPageDynamicBean.IMG:
                initView(helper);

                break;
            case HPageDynamicBean.IMG_TEXT:
                break;
        }
    }

    private void initView(BaseViewHolder helper){
        initBanner((Banner) helper.getView(R.id.home_page_banner));
        final ImageView citySelectIcon=helper.getView(R.id.city_select_icon);
        citySelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isExpand = isExpand == true ? false : true;
                citySelectIcon.setAlpha(0f);
                changeSelectIcon(citySelectIcon);
            }
        });

        helper.getView(R.id.msg_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewClickAnim.clickAnim(view);
            }
        });
    }

    private void changeSelectIcon(final ImageView citySelectIcon) {
        if (isExpand) {
            citySelectIcon.setImageResource(R.mipmap.shousuo);
        } else {
            citySelectIcon.setImageResource(R.mipmap.zhankai);
        }
        citySelectIcon.postDelayed(new Runnable() {
            @Override
            public void run() {
                citySelectIcon.setAlpha(1f);
            }
        }, 100);

    }

    private void initBanner(Banner banner){
        bannerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            bannerList.add(CommonApi.PIC);
        }

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setDelayTime(5000);
        banner.setImages(bannerList);
        //自定义图片加载框架
        banner.setImageLoader(new GlideImageLoader());
        banner.start();

    }
}
