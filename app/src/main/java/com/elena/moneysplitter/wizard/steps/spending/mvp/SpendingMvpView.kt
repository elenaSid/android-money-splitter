package com.elena.moneysplitter.wizard.steps.spending.mvp

import com.elena.domain.item.ItemEntity
import com.elena.moneysplitter.wizard.steps.spending.ui.SpendingData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface SpendingMvpView: MvpView {

    @AddToEndSingle
    fun updateSpendingList(spendingList: List<SpendingData>)

    @AddToEndSingle
    fun showEmptyState()
}