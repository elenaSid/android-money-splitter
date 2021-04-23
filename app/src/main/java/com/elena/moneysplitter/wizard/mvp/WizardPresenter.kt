package com.elena.moneysplitter.wizard.mvp

import com.elena.moneysplitter.wizard.WizardStep
import com.elena.moneysplitter.wizard.canGoBack
import com.elena.moneysplitter.wizard.canGoForward
import moxy.MvpPresenter

/**
 * @author elena
 */
class WizardPresenter : MvpPresenter<WizardMvpView>() {

    private val steps = WizardStep.values()
    private var currentStep = WizardStep.values()[0]

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        setStep(currentStep)
    }


    /**
     * Вызывается при при ручном запросе на смену шага
     */
    fun onNextStepRequested() = setNextStep()

    /**
     * Вызывается при запросе предыдущего шага
     */
    fun onPreviousStepRequested() {
        if (currentStep.canGoBack()) {
            val nextStepIndex = currentStep.value - 1
            setStep(WizardStep.values()[nextStepIndex])
        } else {
            //TODO: Закрыть приложение
        }
    }

    /**
     * Устанавливает следующий шаг онбординга
     */
    private fun setNextStep() {
        if (currentStep.value == steps.size - 1) {
            //TODO: Сохранять общий итог и переходить на какой-то суммарный экран?
        } else {
            setStep(WizardStep.values()[currentStep.value + 1])
        }
    }

    /**
     * Устанавливает в качестве текущего шага онбординга указанный [wizardStep]
     */
    private fun setStep(wizardStep: WizardStep) {
        currentStep = wizardStep
        viewState.setHomeButtonVisibility(wizardStep.canGoBack())
        viewState.setStep(wizardStep.value)
        viewState.setActionButtonEnabled(wizardStep.canGoForward())
        //TODO: Добавить переход на нужный экран
    }
}