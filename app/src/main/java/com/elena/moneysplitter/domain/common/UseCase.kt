package com.elena.moneysplitter.domain.common

import android.support.annotation.CallSuper
import com.elena.moneysplitter.domain.common.exception.DomainException
import com.elena.moneysplitter.domain.common.exception.UseCaseException

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 16:40
 */
abstract class UseCase<P, R> {

    /**
     * Execution of use case
     *
     * @param param params for use case execution
     * @return result of execution
     * @throws UseCaseException errors of domain layer during execution
     */
    @CallSuper
    @Throws(UseCaseException::class)
    fun execute(param: P?): R? {
        try {
            return buildUseCase(param)
        } catch (e: Exception) {
            throw UseCaseException.build(e)
        }

    }

    /**
     * Execution of use case
     *
     * @param param         params for use case execution
     * @param defaultResult default result in case of errors
     * @return result of execution
     */
    fun execute(param: P?, defaultResult: R?): R? {
        try {
            return execute(param)
        } catch (e: UseCaseException) {
            return defaultResult
        }

    }

    /**
     * Inner method for UseCase body
     *
     * @see .execute
     */
    @Throws(DomainException::class)
    protected abstract fun buildUseCase(param: P?): R?
}