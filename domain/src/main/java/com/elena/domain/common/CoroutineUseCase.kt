package com.elena.domain.common

import com.elena.domain.common.exception.UseCaseException

/**
 * @author elena
 */
abstract class CoroutineUseCase<P, R> {

    protected abstract suspend fun runUseCase(param: P): R

    @Throws(UseCaseException::class)
    suspend fun execute(param: P): R {
        try {
            return runUseCase(param)
        } catch (e: Exception) {
            throw UseCaseException.build(e)
        }
    }

    suspend fun execute(param: P, defaultResult: R): R {
        return try {
            runUseCase(param)
        } catch (e: Exception) {
            defaultResult
        }
    }
}
