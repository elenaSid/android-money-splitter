package com.elena.moneysplitter.wizard


/**
 * Класс-enum для шагов визарда
 *
 * @author elena
 */
enum class WizardStep(
    var value: Int,
    val backwardNavigation: BackwardNavigation,
    val forwardNavigation: ForwardNavigation,
) {
    USERS(0, BackwardNavigation.NONE, ForwardNavigation.NEXT),
    GROUPS(1, BackwardNavigation.BACK, ForwardNavigation.SKIP),
    SPENDING(2, BackwardNavigation.BACK, ForwardNavigation.NEXT),
    SUMMARY(3, BackwardNavigation.BACK, ForwardNavigation.NEXT),
    DEBTS(4, BackwardNavigation.BACK, ForwardNavigation.DONE)
}

/**
 * Получает флаг возможности пропуска шага. true - шаг может быть пропущен
 */
fun WizardStep.canSkip() = this.forwardNavigation == ForwardNavigation.SKIP

/**
 * Получает флаг возможности возвращения к предыдущему шагу
 */
fun WizardStep.canGoBack() = this.backwardNavigation == BackwardNavigation.BACK

/**
 * Получает флаг возможности перехода к следующему шагу (при помощи авто пропуска или вручную)
 */
fun WizardStep.canGoForward() = this.forwardNavigation != ForwardNavigation.DONE