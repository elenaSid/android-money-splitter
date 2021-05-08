package com.elena.moneysplitter.spending.mvp

import com.elena.domain.item.ItemEntity
import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.GetAllUsersUseCase
import moxy.MvpPresenter
import java.util.ArrayList

/**
 * @author elena
 */
class SpendingEditPresenter(
        private val getAllUsersUseCase: GetAllUsersUseCase
) : MvpPresenter<SpendingEditMvpView>() {

    private var itemId: Int? = null
    private var item: ItemEntity? = null
    private var itemName: String? = null
    private var price: Float = 0f
    private val usersPayers = mutableListOf<UserEntity>()
    private val usersConsumers = mutableListOf<UserEntity>()
    private var users = mutableListOf<UserEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        users.addAll(getAllUsersUseCase.execute(Unit, emptyList()))

        itemId?.let {
            /*val item = getItemUseCase.execute(it)
            itemName = item.name
            price = item.price
            usersConsumers.addAll()
            usersPayers.addAll()
            this.item = item*/
        }
        viewState.setItemName(itemName)
        updatePayers(true)
        updateConsumers(true)
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

    fun onItemPriceChanged(price: Float) {
        if (price != 0f) {
            this.price = price
        }
        viewState.setItemPrice(price)
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
        //saveItemUseCase.execute(SaveItemUseCase.Param(itemId, itemName!!, price, usersPayers, usersConsumers))
        viewState.saveFinish()
    }

    fun onItemDeleteRequested() {
        /*val itemToDelete = item?.copy()
        itemToDelete?.let {
            deleteItemUseCase.execute(itemToDelete, Unit)
        }*/
        viewState.saveFinish()
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
        //TODO: проверять чтобы был хотя бы один кто платит и хотя бы один кто потребляет, было название и цена
        //viewState.manageSaveBtnAvailability(itemName != null)
    }
}