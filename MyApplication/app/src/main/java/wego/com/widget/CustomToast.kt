package wego.com.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import wego.com.R
import android.opengl.ETC1.getHeight
import android.text.TextUtils
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView


/**
 * Created by Administrator on 2017/12/22.
 */

class CustomToast{


    companion object {
        //加载成功
        val LOAD_SUCCESS:Int=2
        //load_fail
        val LOAD_FAILURE:Int=3
        //网络连接失败
        val NET_FAILURE:Int=4

        fun showToast(mContext: Context,msg :String){
            initStandard(mContext,msg)
        }

        fun showToast(mContext: Context,msg:String?,resId:Int?){
            var view=LayoutInflater.from(mContext).inflate(R.layout.net_connect_fail,null)

            var tImg=view.findViewById<ImageView>(R.id.toast_img)
            var text=view.findViewById<TextView>(R.id.toast_text)

            if(resId!=null){
                tImg.setImageResource(resId)
            }

            if(!TextUtils.isEmpty(text.toString())){
                text.text=msg
            }


            var toast=Toast(mContext)
            val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val height = wm.defaultDisplay.height
            toast.setGravity(Gravity.TOP, 0, height / 3)
            toast.duration=Toast.LENGTH_SHORT
            toast.view=view
            toast.show()
        }
        fun showToast(mContext: Context,type:Int){
            when(type){
                LOAD_SUCCESS->loadSuc(mContext)
                LOAD_FAILURE-> loadFail(mContext)
                NET_FAILURE->loadNetFail(mContext)
            }
        }

        private fun loadNetFail(mContext: Context){
            var view=LayoutInflater.from(mContext).inflate(R.layout.net_connect_fail,null)

            var toast=Toast(mContext)
            val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val height = wm.defaultDisplay.height
            toast.setGravity(Gravity.TOP, 0, height / 3)
            toast.duration=Toast.LENGTH_SHORT
            toast.view=view
            toast.show()
        }

        private fun loadFail(mContext: Context){
            var view=LayoutInflater.from(mContext).inflate(R.layout.load_fail,null)

            var toast=Toast(mContext)
            val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val height = wm.defaultDisplay.height
            toast.setGravity(Gravity.TOP, 0, height / 3)
            toast.duration=Toast.LENGTH_SHORT
            toast.view=view
            toast.show()
        }
        private fun loadSuc(mContext: Context){
            var view=LayoutInflater.from(mContext).inflate(R.layout.load_success,null)

            var toast=Toast(mContext)
            val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val height = wm.defaultDisplay.height
            toast.setGravity(Gravity.TOP, 0, height / 3)
            toast.duration=Toast.LENGTH_SHORT
            toast.view=view
            toast.show()
        }


        private fun initStandard(mContext: Context,msg :String){
            var view=LayoutInflater.from(mContext).inflate(R.layout.toast_layout,null)
            var textView=view.findViewById<TextView>(R.id.toast_txt)
            textView.text=msg
            var toast=Toast(mContext)
            val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val height = wm.defaultDisplay.height
            toast.setGravity(Gravity.TOP, 0, height / 3)
            toast.duration=Toast.LENGTH_LONG
            toast.view=view
            toast.show()
        }
    }


}