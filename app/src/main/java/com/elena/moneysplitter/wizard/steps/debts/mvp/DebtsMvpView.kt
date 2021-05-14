package com.elena.moneysplitter.wizard.steps.debts.mvp

import com.elena.domain.summary.OptimizedTransactionForFamily
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip

/**
 * @author elena
 */
interface DebtsMvpView: MvpView {

    @AddToEndSingle
    fun updateDebts(debts: Set<OptimizedTransactionForFamily>)

    @SingleState
    fun copyAllDebtsToClipboard(debts: Set<OptimizedTransactionForFamily>)
}