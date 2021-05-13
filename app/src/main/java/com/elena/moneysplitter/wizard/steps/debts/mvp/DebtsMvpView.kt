package com.elena.moneysplitter.wizard.steps.debts.mvp

import com.elena.domain.summary.OptimizedTransactionForFamily
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author elena
 */
interface DebtsMvpView: MvpView {

    @AddToEndSingle
    fun updateDebts(debts: Set<OptimizedTransactionForFamily>)
}