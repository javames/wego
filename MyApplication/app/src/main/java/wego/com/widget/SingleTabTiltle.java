package wego.com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wego.com.R;
import wego.com.interz.Interz;

/**
 * Created by Administrator on 2017/10/31.
 */

public class SingleTabTiltle extends RelativeLayout {

    private ImageView leftIcon;
    private TextView title;
    private ImageView rightIcon;

    private FrameLayout leftBtn;
    private FrameLayout rightBtn;

    private int defBgColor;

    private Interz.OnClickListener onClickListener;

    public SingleTabTiltle(Context context) {
        this(context,null);
    }
    public SingleTabTiltle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SingleTabTiltle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        setBackgroundColor(getResources().getColor(R.color.main_blue));

        View view = LayoutInflater.from(context).inflate(R.layout.tab_title, this);


        leftBtn=view.findViewById(R.id.left_icon_btn);
        rightBtn=view.findViewById(R.id.right_icon_btn);

        leftIcon=view.findViewById(R.id.left_icon);
        rightIcon=view.findViewById(R.id.right_icon);

        title=view.findViewById(R.id.tab_title_txt);


        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickListener!=null){
                    onClickListener.onClick(view);
                }
            }
        });

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickListener!=null){
                    onClickListener.onClick(view);
                }
            }
        });

    }

    public void setBgColor(int colorId){
        setBackgroundColor(colorId);
    }
    public void setLeftIcon(int resId){
        leftIcon.setImageResource(resId);
    }

    public void setRightIcon(int resId){
        rightIcon.setImageResource(resId);
    }

    public void setTitle(String text){
        title.setText(text);
    }

    public void setLeftClickListener(Interz.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    public void setTitleColor(int titleColor){
        title.setTextColor(titleColor);
    }

}
