package wego.com.widget

import android.content.Context
import android.widget.Toast

/**
 * Created by Administrator on 2017/12/22.
 */
fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()