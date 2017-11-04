package wego.com.interz;

import android.view.View;

/**
 * Created by Administrator on 2017/11/1.
 */

public interface Interz {

    interface OnClickListener{
        void onClick(View view);
    }

    interface ParseResponse{
        void error(String error);
        void success(Object obj);
    }

}
