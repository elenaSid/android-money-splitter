package com.elena.moneysplitter.wizard.steps.debts.mvp

import com.elena.domain.summary.OptimizedTransactionForFamily
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState

/**
 * @author elena
 */
interface DebtsMvpView: MvpView {

    @AddToEndSingle
    fun setEmptyState()

    @AddToEndSingle
    fun updateDebts(debts: Set<OptimizedTransactionForFamily>)

    @SingleState
    fun copyAllDebtsToClipboard(debts: Set<OptimizedTransactionForFamily>)
}