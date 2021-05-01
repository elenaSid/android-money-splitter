package com.elena.moneysplitter.family.mvp

import com.elena.domain.family.interaction.GetFamilyWithMembersUseCase
import com.elena.domain.family.interaction.SaveFamilyUseCase
import com.elena.domain.user.UserEntity
import com.elena.domain.user.interaction.GetUsersWithoutFamilyUseCase
import moxy.MvpPresenter

/**
 * @author elena
 */
class FamilyEditPresenter(
        private val getUsersWithoutFamilyUseCase: GetUsersWithoutFamilyUseCase,
        private val getFamilyWithMembersUseCase: GetFamilyWithMembersUseCase,
        private val saveFamilyUseCase: SaveFamilyUseCase
) : MvpPresenter<FamilyEditMvpView>() {

    private var familyId: Int? = null
    private var familyName: String? = null
    private val usersInFamily = mutableListOf<UserEntity>()
    private var users = mutableListOf<UserEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        users.addAll(getUsersWithoutFamilyUseCase.execute(Unit, emptyList()))

        familyId?.let {
            val familyMembers = getFamilyWithMembersUseCase.execute(it)
            familyName = familyMembers.family.name
            usersInFamily.addAll(familyMembers.users)
            users.addAll(usersInFamily)
        }
        viewState.setFamilyName(familyName)
        if (users.size <= 1) {
            viewState.showEmptyState()
        } else {
            users = users.sortedBy { !usersInFamily.contains(it) }.toMutableList()
            updateFamilyMembers()
        }
    }

    fun onFamilyIdParsed(familyId: Int) {
        this.familyId = familyId
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
        if (usersInFamily.contains(user)) {
            usersInFamily.remove(user)
        } else {
            usersInFamily.add(user)
        }
        updateFamilyMembers()
        updateSaveState()
    }

    fun onFamilySaveRequested() {
        saveFamilyUseCase.execute(SaveFamilyUseCase.Param(familyId, familyName!!, usersInFamily))
        viewState.saveFinish()
    }

    fun onFamilyDeleteRequested() {
        //TODO: Добавить удаление семьи
        viewState.saveFinish()
    }

    private fun updateFamilyMembers() {
        viewState.updateFamilyMembers(users, usersInFamily)
    }

    private fun updateSaveState() {
        viewState.manageSaveBtnAvailability(familyName != null && usersInFamily.size > 1)
    }
}