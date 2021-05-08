package com.elena.moneysplitter.extras

import android.text.Editable

/**
 * @author elena
 */
private const val TEXT_TRIM = "( +|\\t|\\r?\\n)+"
private const val NUMBERS_TRIM = "@\"^-?[0-9][0-9,\\.]+\$\""

fun Editable.trimmedContent() = this.toString().replace(TEXT_TRIM.toRegex(), " ")

fun Editable.trimmedNumberContent() = this.toString().replace(NUMBERS_TRIM.toRegex(), "")