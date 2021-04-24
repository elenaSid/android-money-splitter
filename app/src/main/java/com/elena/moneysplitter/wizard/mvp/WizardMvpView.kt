package com.elena.moneysplitter.wizard.mvp

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

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
     * Команда установки шага визарда
     */
    @AddToEndSingle
    fun setStep(stepIndex: Int)
}