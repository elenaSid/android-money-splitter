package com.elena.moneysplitter.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * @author elena
 *         Date: 04/01/2019
 *         Time: 16:22
 */
object DisplayUtils {

    /**
     * Transform dpi to pixels
     *
     * @param dp value
     * @return px value
     */
    fun dpToPx(resources: Resources, dp: Float): Int {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, resources.displayMetrics))
    }
}