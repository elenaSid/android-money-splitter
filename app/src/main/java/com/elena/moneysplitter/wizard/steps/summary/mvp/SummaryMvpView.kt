package com.elena.moneysplitter.wizard.steps.summary.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface SummaryMvpView: MvpView {

    @AddToEndSingle
    fun updateSummaryList()
}