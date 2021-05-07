package com.elena.moneysplitter.wizard.steps.spendings.mvp

import com.elena.domain.item.ItemEntity
import com.elena.domain.item.interaction.GetAllItemsUseCase
import com.elena.moneysplitter.navigation.WizardNavigationScreen
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

/**
 * @author elena
 */
class SpendingPresenter(
        private val router: Router,
        private val getAllItemsUseCase: GetAllItemsUseCase
) : MvpPresenter<SpendingMvpView>() {

    override fun attachView(view: SpendingMvpView?) {
        super.attachView(view)
        updateItems()
    }

    fun onItemEditClicked(item: ItemEntity) {
        //router.navigateTo(WizardNavigationScreen.itemEdit(item.id))
    }

    private fun updateItems() {
        val items = getAllItemsUseCase.execute(Unit, emptyList())
        if (items.isEmpty()) {
            viewState.showEmptyState()
        } else {
            viewState.updateItems(items)
        }
    }
}