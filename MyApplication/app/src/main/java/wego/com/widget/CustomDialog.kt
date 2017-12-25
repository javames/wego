package wego.com.widget

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import wego.com.R

/**
 * Created by Administrator on 2017/12/22.
 */
class CustomDialog {


    companion object {
        var dialog:Dialog?=null
        //用于支付宝/微信支付等待是弹窗
        fun showDialog(context:Context){

            if(dialog==null){
                dialog=Dialog(context,R.style.BottomDialog)
                var view=LayoutInflater.from(context).inflate(R.layout.dialog_pay_monent,null)
                dialog?.setContentView(view)
                dialog?.setCancelable(true)
                dialog?.setCanceledOnTouchOutside(false)
            }

            dialog?.show()


        }
    }
}