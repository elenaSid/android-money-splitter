package com.elena.domain.common

import com.elena.domain.common.exception.DomainException
import com.elena.domain.common.exception.UseCaseException

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
    @Throws(UseCaseException::class)
    fun execute(param: P): R {
        try {
            return runUseCase(param)
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
    fun execute(param: P, defaultResult: R): R {
        return try {
            execute(param)
        } catch (e: UseCaseException) {
            defaultResult
        }
    }

    /**
     * Inner method for UseCase body
     *
     * @see .execute
     */
    @Throws(DomainException::class)
    protected abstract fun runUseCase(param: P): R

    /**
     * Inner method for using UseCase inside other UseCases
     */
    @Throws(DomainException::class)
    fun use(param: P): R? = runUseCase(param)
}