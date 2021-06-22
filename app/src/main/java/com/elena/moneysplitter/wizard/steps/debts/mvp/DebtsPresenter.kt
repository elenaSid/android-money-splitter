package com.elena.moneysplitter.wizard.steps.debts.mvp

import com.elena.domain.summary.OptimizedTransactionForFamily
import com.elena.domain.summary.interaction.GetOptimizedTransactionsForAllFamiliesUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author elena
 */
class DebtsPresenter(
        private val getOptimizedTransactionsForAllFamiliesUseCase: GetOptimizedTransactionsForAllFamiliesUseCase
) : CoroutineMvpPresenter<DebtsMvpView>() {

    lateinit var debts: Set<OptimizedTransactionForFamily>
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            debts = getOptimizedTransactionsForAllFamiliesUseCase.execute(Unit, emptySet())
            withContext(Dispatchers.Main) {
                if (debts.isEmpty()) {
                    viewState.setEmptyState()
                } else {
                    viewState.updateDebts(debts)
                }
            }
        }
    }

    fun onCopiedAsTextClicked() {
        viewState.copyAllDebtsToClipboard(debts)
    }
}