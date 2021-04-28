package com.elena.moneysplitter.extras

import android.text.Editable

/**
 * @author elena
 */
private const val TEXT_TRIM = "( +|\\t|\\r?\\n)+"

fun Editable.trimmedContent() = this.toString().replace(TEXT_TRIM.toRegex(), " ")