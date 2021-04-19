package com.elena.domain.common.exception

import io.reactivex.Flowable

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 23:58
 */
abstract class RxFlowableUseCase<P, R> : RxUseCase<P, Flowable<R>>() {

    fun execute(param: P): Flowable<R> {
        return runUseCase(param).onErrorResumeNext({ throwable: Throwable ->
            val newThrowable = UseCaseException.build(throwable)
            Flowable.error<R>(newThrowable)
        })
    }

    fun execute(param: P, defaultValue: R): Flowable<R> {
        return execute(param).onErrorReturnItem(defaultValue)
    }

}