package com.elena.moneysplitter.navigation

import com.elena.moneysplitter.wizard.steps.families.ui.FamiliesFragment
import com.elena.moneysplitter.wizard.steps.users.ui.UsersFragment
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
     * Формирует экземпляр шага визарда - добавление групп пользователей
     */
    fun familiesStep() = FragmentScreen { FamiliesFragment() }
}