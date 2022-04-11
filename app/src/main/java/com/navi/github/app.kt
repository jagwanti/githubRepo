@file:JvmName("Utils")
package com.navi.github

import android.text.TextUtils
import java.text.SimpleDateFormat


fun parseDateString(date: String?): String {
    return try {
        if(!TextUtils.isEmpty(date)) {
            val tempDate =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date) //"2011-01-26T19:01:12Z"
            SimpleDateFormat("H:mm 'on' dd MMM ").format(tempDate)
        }else ""
    }catch (e:Exception){
        ""
    }
}
