package com.elena.moneysplitter.wizard.steps.spendings.mvp

import com.elena.domain.item.ItemEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface SpendingMvpView: MvpView {

    @AddToEndSingle
    fun updateItems(items: List<ItemEntity>)

    @AddToEndSingle
    fun showEmptyState()
}