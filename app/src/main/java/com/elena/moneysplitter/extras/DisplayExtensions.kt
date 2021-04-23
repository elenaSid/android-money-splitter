package com.elena.moneysplitter.extras

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

/**
 * Расширение для преобразования dp в пиксели
 *
 * @author elena
 */
fun Int.toPx() = this.toFloat().toPx()

fun Float.toPx() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
).roundToInt()