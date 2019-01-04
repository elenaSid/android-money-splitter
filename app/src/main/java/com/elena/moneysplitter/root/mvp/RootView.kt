package com.elena.moneysplitter.root.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:06
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface RootView : MvpView {
    fun showEmptyView()
}