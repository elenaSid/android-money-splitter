package com.elena.moneysplitter.extras

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

/**
 * @author elena
 */
object KeyboardManager {

    fun hide(context: Context, windowToken: IBinder) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(windowToken, 0)
    }
}