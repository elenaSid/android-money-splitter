package com.elena.moneysplitter.family.mvp

import com.elena.domain.family.interaction.SaveFamilyUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.GetAllUsersUseCase
import moxy.MvpPresenter

/**
 * @author elena
 */
class FamilyEditPresenter(
        private val getAllUsersUseCase: GetAllUsersUseCase,
        private val saveFamilyUseCase: SaveFamilyUseCase
) : MvpPresenter<FamilyEditMvpView>() {

    private var familyId: Int? = null
    private var familyName: String? = null
    private lateinit var users: List<UserEntity>
    private val familyMembers = mutableListOf<UserEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        users = getAllUsersUseCase.execute(Unit, emptyList())
        //TODO: Добавить выборку добавленных членов семьи
        users.sortedBy { !familyMembers.contains(it) }

        updateFamilyMembers()
    }

    fun onFamilyNameChanged(familyName: String) {
        val isNotEmpty = familyName.isNotEmpty()
        if (isNotEmpty) {
            this.familyName = familyName
        }
        viewState.manageErrorMessage(isNotEmpty)
        updateSaveState()
    }

    fun onUserSelected(user: UserEntity) {
        if (familyMembers.contains(user)) {
            familyMembers.remove(user)
        } else {
            familyMembers.add(user)
        }
        updateFamilyMembers()
        updateSaveState()
    }

    fun onFamilySaveRequested() {
        saveFamilyUseCase.execute(SaveFamilyUseCase.Param(familyId, familyName!!, familyMembers))
        viewState.saveFinish()
    }

    private fun updateFamilyMembers() {
        viewState.updateFamilyMembers(users, familyMembers)
    }

    private fun updateSaveState() {
        viewState.manageSaveBtnAvailability(familyName != null && familyMembers.isNotEmpty())
    }
}