package com.elena.moneysplitter.wizard.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

/**
 * @author elena
 */
interface WizardMvpView : MvpView {

    /**
     * Команда отображения кнопки "домой", где [isHomeButtonVisible] флаг видимости кнопки
     */
    @AddToEndSingle
    fun setHomeButtonVisibility(isHomeButtonVisible: Boolean)

    /**
     * Команда обновления действия кнопки перехода на следующий шаг,
     * [isActionDone] true если кнопка завершает визард, false - переключает на следующий шаг
     */
    @AddToEndSingle
    fun updateActionButton(isActionDone: Boolean)

    /**
     * Команда обновления состояния кнопки перехода на следующий шаг,
     * [isEnabled] true если кнопка доступна, false - заблокирована
     */
    @AddToEndSingle
    fun setActionButtonEnabled(isEnabled: Boolean)

    /**
     * Команда отображения кнопки "добавить", где [isVisible] флаг видимости кнопки
     */
    @AddToEndSingle
    fun setFABVisibility(isVisible: Boolean)

    /**
     * Команда отображения диалогового окна с подтверждением сброса данных
     */
    @Skip
    fun showConfirmationDialog()

    /**
     * Команда обновления подписи с текущим шагом
     */
    @AddToEndSingle
    fun updateStepCount(currentStepIndex: Int, stepsCount: Int)
}