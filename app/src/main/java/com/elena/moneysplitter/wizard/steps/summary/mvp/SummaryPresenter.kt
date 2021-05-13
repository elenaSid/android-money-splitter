package com.elena.moneysplitter.wizard.steps.summary.mvp

import com.elena.domain.summary.interaction.GetSummaryForAllFamiliesUseCase
import moxy.MvpPresenter

/**
 * @author elena
 */
class SummaryPresenter(
        private val getSummaryForAllFamiliesUseCase: GetSummaryForAllFamiliesUseCase
) : MvpPresenter<SummaryMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val summarySet = getSummaryForAllFamiliesUseCase.execute(Unit, emptySet())
        viewState.updateSummaryList(summarySet)
    }
}