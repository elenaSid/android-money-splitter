package com.elena.moneysplitter.domain.common.exception

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 16:41
 */
class UseCaseException
/**
 * @param parentException исключение, из-за которого произошел сбой в работе сценария
 */
private constructor(
        /**
         * Return the error which was the reason why useCase was stopped
         *
         * @return [Exception]
         */
        val parentException: Throwable?) : DomainException() {
    companion object {

        /**
         * @see .UseCaseException
         */
        fun build(parentException: Throwable?): UseCaseException {
            return UseCaseException(parentException)
        }

        /**
         * Check class of exception
         *
         * @param throwable      [Throwable]
         * @param exceptionClass class of exception which is expected
         * @return true if expected exception class is equal to useCase exception
         */
        fun checkParentException(throwable: Throwable,
                                 exceptionClass: Class<out Exception>): Boolean {
            if (throwable !is UseCaseException) {
                return false
            }

            val parentThrowable = throwable.parentException
            return parentThrowable != null && exceptionClass.isInstance(parentThrowable)
        }
    }
}