package com.elena.moneysplitter.wizard.steps.debts.mvp

import com.elena.domain.summary.OptimizedTransactionForFamily
import com.elena.domain.summary.interaction.GetOptimizedTransactionsForAllFamiliesUseCase
import moxy.MvpPresenter

/**
 * @author elena
 */
class DebtsPresenter(
        private val getOptimizedTransactionsForAllFamiliesUseCase: GetOptimizedTransactionsForAllFamiliesUseCase
) : MvpPresenter<DebtsMvpView>() {

    lateinit var debts: Set<OptimizedTransactionForFamily>
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        debts = getOptimizedTransactionsForAllFamiliesUseCase.execute(Unit, emptySet())
        if (debts.isEmpty()) {
            viewState.setEmptyState()
        } else {
            viewState.updateDebts(debts)
        }
    }

    fun onCopiedAsTextClicked() {
        viewState.copyAllDebtsToClipboard(debts)
    }
}