package com.elena.domain.common

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 21:08
 */
abstract class RxUseCase<P, R> {

    protected abstract fun runUseCase(param: P): R

    fun use(param: P): R {
        return runUseCase(param)
    }
}
