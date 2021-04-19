package com.elena.domain.common

import com.elena.domain.common.exception.RxUseCase
import com.elena.domain.common.exception.UseCaseException
import io.reactivex.Single

/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 21:03
 */
abstract class RxSingleUseCase<P, R> : RxUseCase<P, Single<R>>() {

    fun execute(param: P): Single<R> {
        return runUseCase(param).onErrorResumeNext({ throwable ->
            val newThrowable = UseCaseException.build(throwable)
            Single.error<R>(newThrowable)
        })
    }

    fun execute(param: P, defaultValue: R): Single<R> {
        return execute(param).onErrorReturnItem(defaultValue)
    }
}