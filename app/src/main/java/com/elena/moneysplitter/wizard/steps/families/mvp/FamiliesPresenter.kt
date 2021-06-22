package com.elena.moneysplitter.wizard.steps.families.mvp

import com.elena.domain.family.FamilyEntity
import com.elena.domain.family.interaction.GetAllFamiliesWithMembersUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import com.elena.moneysplitter.navigation.WizardNavigationScreen
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author elena
 */
class FamiliesPresenter(
        private val router: Router,
        private val getAllFamiliesWithMembersUseCase: GetAllFamiliesWithMembersUseCase
) : CoroutineMvpPresenter<FamiliesMvpView>() {

    override fun attachView(view: FamiliesMvpView?) {
        super.attachView(view)
        updateFamilies()
    }

    fun onFamilyEditClicked(family: FamilyEntity) {
        router.navigateTo(WizardNavigationScreen.familyEdit(family.id))
    }

    private fun updateFamilies() {
        launch {
            val families = getAllFamiliesWithMembersUseCase.execute(Unit, emptyList())
            withContext(Dispatchers.Main) {
                if (families.isEmpty()) {
                    viewState.showEmptyState()
                } else {
                    viewState.updateFamilies(families)
                }
            }
        }
    }
}