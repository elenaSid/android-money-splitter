package com.elena.moneysplitter.root.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle


/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:06
 */
interface RootView : MvpView {
    @AddToEndSingle
    fun showEmptyView()
}