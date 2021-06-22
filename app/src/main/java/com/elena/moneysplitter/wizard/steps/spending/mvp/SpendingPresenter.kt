package com.elena.moneysplitter.wizard.steps.spending.mvp

import com.elena.domain.item.ItemEntity
import com.elena.domain.item.interaction.GetAllItemsUseCase
import com.elena.domain.user.interaction.GetUsersUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import com.elena.moneysplitter.navigation.WizardNavigationScreen
import com.elena.moneysplitter.wizard.mvp.PARAM_IS_STEP_READY
import com.elena.moneysplitter.wizard.steps.spending.ui.SpendingData
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author elena
 */
class SpendingPresenter(
        private val router: Router,
        private val getUsersUseCase: GetUsersUseCase,
        private val getAllItemsUseCase: GetAllItemsUseCase
) : CoroutineMvpPresenter<SpendingMvpView>() {

    override fun attachView(view: SpendingMvpView?) {
        super.attachView(view)
        updateItems()
    }

    fun onSpendingEditClicked(itemId: Int) {
        router.navigateTo(WizardNavigationScreen.spendingEdit(itemId))
    }

    private fun updateItems() {
        launch {
            val items = getAllItemsUseCase.execute(Unit, emptyList())
            if (items.isEmpty()) {
                withContext(Dispatchers.Main) { viewState.showEmptyState() }
            } else {
                val spendingList = getSpendingList(items)
                withContext(Dispatchers.Main) { viewState.updateSpendingList(spendingList) }
            }
            withContext(Dispatchers.Main) {
                router.sendResult(PARAM_IS_STEP_READY, items.isNotEmpty())
            }
        }
    }

    private suspend fun getSpendingList(items: List<ItemEntity>) = items.map { item ->
        val payers = getUsersUseCase.execute(item.payedByUserIds, emptyList()).map { it.name }
        val consumers = getUsersUseCase.execute(item.usedByUserIds, emptyList()).map { it.name }
        SpendingData(item.id, item.name, item.price, payers, consumers)
    }
}