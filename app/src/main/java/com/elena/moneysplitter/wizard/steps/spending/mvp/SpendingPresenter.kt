package com.elena.moneysplitter.wizard.steps.spending.mvp

import com.elena.domain.item.interaction.GetAllItemsUseCase
import com.elena.domain.user.interaction.GetUsersUseCase
import com.elena.moneysplitter.navigation.WizardNavigationScreen
import com.elena.moneysplitter.wizard.mvp.PARAM_IS_STEP_READY
import com.elena.moneysplitter.wizard.steps.spending.ui.SpendingData
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

/**
 * @author elena
 */
class SpendingPresenter(
        private val router: Router,
        private val getUsersUseCase: GetUsersUseCase,
        private val getAllItemsUseCase: GetAllItemsUseCase
) : MvpPresenter<SpendingMvpView>() {

    override fun attachView(view: SpendingMvpView?) {
        super.attachView(view)
        updateItems()
    }

    fun onSpendingEditClicked(itemId: Int) {
        router.navigateTo(WizardNavigationScreen.spendingEdit(itemId))
    }

    private fun updateItems() {
        val items = getAllItemsUseCase.execute(Unit, emptyList())
        if (items.isEmpty()) {
            viewState.showEmptyState()
        } else {
            val spendingList = items.map { item ->
                val payers = getUsersUseCase.execute(item.payedByUserIds, emptyList()).map { it.name }
                val consumers = getUsersUseCase.execute(item.usedByUserIds, emptyList()).map { it.name }
                SpendingData(item.id, item.name, item.price, payers, consumers)
            }
            viewState.updateSpendingList(spendingList)
        }
        router.sendResult(PARAM_IS_STEP_READY, items.isNotEmpty())
    }
}