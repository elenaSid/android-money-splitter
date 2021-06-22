package com.elena.moneysplitter.wizard.steps.summary.mvp

import com.elena.domain.summary.interaction.GetSummaryForAllFamiliesUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author elena
 */
class SummaryPresenter(
        private val getSummaryForAllFamiliesUseCase: GetSummaryForAllFamiliesUseCase
) : CoroutineMvpPresenter<SummaryMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val summarySet = getSummaryForAllFamiliesUseCase.execute(Unit, emptySet())
            withContext(Dispatchers.Main) { viewState.updateSummaryList(summarySet) }
        }
    }
}