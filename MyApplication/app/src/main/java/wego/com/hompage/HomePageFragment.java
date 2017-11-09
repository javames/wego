package wego.com.hompage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wego.com.R;
import wego.com.common.BaseLazyFragment;

/**
 * Created by Administrator on 2017/10/31.
 */

public class HomePageFragment extends BaseLazyFragment {

    private Unbinder bind;
    @BindView(R.id.home_page_banner)
    Banner banner;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.city_select_icon)
    ImageView citySelectIcon;

    private boolean isExpand;
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

    @OnClick({R.id.msg_btn,R.id.city_select_icon})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.msg_btn:
//                view.setAlpha(0f);
                Animation animation = new AlphaAnimation(0f, 1.0f);
                animation.setDuration(1000);
                view.startAnimation(animation);

//                AnimationSet animationSet = new AnimationSet(true);
//                ScaleAnimation scaleAnimation = new ScaleAnimation(
//                        0, 0.1f,0,0.1f,
//                        Animation.RELATIVE_TO_SELF,0.5f,
//                        Animation.RELATIVE_TO_SELF,0.5f);
//                scaleAnimation.setDuration(1000);
//                animationSet.addAnimation(scaleAnimation);
//                view.startAnimation(animationSet);

                break;
            case R.id.city_select_icon:
                isExpand=isExpand==true?false:true;
                citySelectIcon.setAlpha(0f);
                changeSelectIcon();
                break;
        }
    }

    private void changeSelectIcon(){
        if(isExpand){
            citySelectIcon.setImageResource(R.mipmap.shousuo);
        }else{
            citySelectIcon.setImageResource(R.mipmap.zhankai);
        }
        citySelectIcon.postDelayed(new Runnable() {
            @Override
            public void run() {
                citySelectIcon.setAlpha(1f);
            }
        },100);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
