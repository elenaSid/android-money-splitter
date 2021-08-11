package com.elena.moneysplitter.intro.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.elena.moneysplitter.R

/**
 * @author elena
 */
enum class IntroType(@StringRes val titleRes: Int, @DrawableRes val drawableRes: Int) {
    BILL(R.string.intro_add_spending, R.drawable.bill_anim),
    SPENDING(R.string.intro_add_group_spending, R.drawable.bill_anim),
    DEBTS(R.string.intro_add_get_optimized_debts_list, R.drawable.bill_anim)
}