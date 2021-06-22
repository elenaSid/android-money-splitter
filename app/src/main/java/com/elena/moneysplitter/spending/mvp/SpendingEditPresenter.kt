package com.elena.moneysplitter.spending.mvp

import com.elena.domain.item.ItemEntity
import com.elena.domain.item.interaction.DeleteItemUseCase
import com.elena.domain.item.interaction.GetItemUseCase
import com.elena.domain.item.interaction.SaveItemUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.GetAllUsersUseCase
import com.elena.domain.user.interaction.GetUsersUseCase
import com.elena.moneysplitter.extras.CoroutineMvpPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

/**
 * @author elena
 */
class SpendingEditPresenter(
        private val getAllUsersUseCase: GetAllUsersUseCase,
        private val deleteItemUseCase: DeleteItemUseCase,
        private val saveItemUseCase: SaveItemUseCase,
        private val getUsersUseCase: GetUsersUseCase,
        private val getItemUseCase: GetItemUseCase
) : CoroutineMvpPresenter<SpendingEditMvpView>() {

    private var itemId: Int? = null
    private var item: ItemEntity? = null
    private var itemName: String? = null
    private var price: Double = 0.0
    private val usersPayers = mutableListOf<UserEntity>()
    private val usersConsumers = mutableListOf<UserEntity>()
    private var users = mutableListOf<UserEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        launch {
            users.addAll(getAllUsersUseCase.execute(Unit, emptyList()))
            itemId?.let {
                val itemEntity = getItemUseCase.execute(it)
                itemName = itemEntity.name
                price = itemEntity.price
                usersConsumers.addAll(getUsersUseCase.execute(itemEntity.usedByUserIds, emptyList()))
                usersPayers.addAll(getUsersUseCase.execute(itemEntity.payedByUserIds, emptyList()))
                item = itemEntity
            }
            withContext(Dispatchers.Main){
                viewState.setItemName(itemName)
                viewState.setItemPrice(price)
                updatePayers(true)
                updateConsumers(true)
                updateSaveState()
            }
        }
    }

    fun onItemIdParsed(itemId: Int) {
        this.itemId = itemId
    }

    fun onItemNameChanged(itemName: String) {
        val isNotEmpty = itemName.isNotEmpty()
        if (isNotEmpty) {
            this.itemName = itemName
        }
        updateSaveState()
    }

    fun onItemPriceChanged(price: Double) {
        if (price != 0.0) {
            this.price = price
        }
        updateSaveState()
    }

    fun onPayersSelected(payers: List<UserEntity>) {
        if (usersPayers.containsAll(payers)) {
            usersPayers.removeAll(payers)
        } else {
            usersPayers.addAll(payers)
        }
        updatePayers()
        updateSaveState()
    }

    fun onConsumersSelected(consumers: List<UserEntity>) {
        if (usersConsumers.containsAll(consumers)) {
            usersConsumers.removeAll(consumers)
        } else {
            usersConsumers.addAll(consumers)
        }
        updateConsumers()
        updateSaveState()
    }

    fun onItemSaveRequested() {
        launch {
            saveItemUseCase.execute(
                    SaveItemUseCase.Param(itemId, itemName!!, price, usersPayers, usersConsumers)
            )
            withContext(Dispatchers.Main) { viewState.saveFinish() }
        }
    }

    fun onItemDeleteRequested() {
        launch {
            val itemToDelete = item?.copy()
            itemToDelete?.let {
                deleteItemUseCase.execute(itemToDelete, Unit)
            }
            withContext(Dispatchers.Main) { viewState.saveFinish() }
        }
    }

    private fun updatePayers(isSorted: Boolean = false) {
        val sortedUsers = ArrayList(users)
        if (isSorted) {
            sortedUsers.sortBy { !usersPayers.contains(it) }
        }
        viewState.updatePayerUsers(sortedUsers, usersPayers)
    }

    private fun updateConsumers(isSorted: Boolean = false) {
        val sortedUsers = ArrayList(users)
        if (isSorted) {
            sortedUsers.sortBy { !usersConsumers.contains(it) }
        }
        viewState.updateConsumerUsers(sortedUsers, usersConsumers)
    }

    private fun updateSaveState() {
        viewState.manageSaveBtnAvailability(
                itemName != null && price > 0.0 &&
                        usersConsumers.isNotEmpty() && usersPayers.isNotEmpty()
        )
    }
}