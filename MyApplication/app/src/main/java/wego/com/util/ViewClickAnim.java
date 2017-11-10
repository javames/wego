package wego.com.util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ViewClickAnim {

    public static void clickAnim(View view){
        Animation animation = new AlphaAnimation(0f, 1.0f);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }
}
