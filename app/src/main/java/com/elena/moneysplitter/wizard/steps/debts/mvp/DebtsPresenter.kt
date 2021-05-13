package com.elena.moneysplitter.wizard.steps.debts.mvp

import com.elena.domain.summary.interaction.GetOptimizedTransactionsForAllFamiliesUseCase
import moxy.MvpPresenter

/**
 * @author elena
 */
class DebtsPresenter(
        private val getOptimizedTransactionsForAllFamiliesUseCase: GetOptimizedTransactionsForAllFamiliesUseCase
) : MvpPresenter<DebtsMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val debts = getOptimizedTransactionsForAllFamiliesUseCase.execute(Unit, emptySet())
        viewState.updateDebts(debts)
    }
}