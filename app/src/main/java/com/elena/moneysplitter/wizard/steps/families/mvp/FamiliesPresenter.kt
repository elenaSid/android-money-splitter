package com.elena.moneysplitter.wizard.steps.families.mvp

import com.elena.domain.family.interaction.GetAllFamiliesWithMembersUseCase
import moxy.MvpPresenter

/**
 * @author elena
 */
class FamiliesPresenter(
        private val getAllFamiliesWithMembersUseCase: GetAllFamiliesWithMembersUseCase
) : MvpPresenter<FamiliesMvpView>() {

    override fun attachView(view: FamiliesMvpView?) {
        super.attachView(view)
        updateFamilies()
    }

    private fun updateFamilies() {
        val families = getAllFamiliesWithMembersUseCase.execute(Unit, emptyList())
        if (families.isEmpty()) {
            viewState.showEmptyState()
        } else {
            viewState.updateFamilies(families)
        }
    }
}