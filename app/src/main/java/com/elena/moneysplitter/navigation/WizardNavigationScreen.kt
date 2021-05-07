package com.elena.moneysplitter.navigation

import android.content.Intent
import com.elena.moneysplitter.family.ui.FamilyEditActivity
import com.elena.moneysplitter.wizard.steps.families.ui.FamiliesFragment
import com.elena.moneysplitter.wizard.steps.spendings.ui.SpendingFragment
import com.elena.moneysplitter.wizard.steps.users.ui.UsersFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * @author elena
 */
object WizardNavigationScreen {

    /**
     * Формирует экземпляр шага визарда - добавление участников
     */
    fun usersStep() = FragmentScreen { UsersFragment() }

    /**
     * Формирует экземпляр шага визарда - добавление групп пользователей/семей
     */
    fun familiesStep() = FragmentScreen { FamiliesFragment() }

    /**
     * Формирует экземплар экрана редактирования семьи/группы пользователей
     */
    fun familyEdit(familyId: Int? = null) = ActivityScreen {
        if (familyId == null) {
            Intent(it, FamilyEditActivity::class.java)
        } else {
            FamilyEditActivity.getInstance(it, familyId)
        }
    }

    /**
     * Формирует экземпляр шага визарда - добавление трат
     */
    fun spendingStep() = FragmentScreen { SpendingFragment() }
}