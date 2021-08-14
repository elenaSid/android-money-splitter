package com.elena.domain.common.exception

/**
 * @author elena
 */
class UseCaseException : DomainException() {
    companion object {
        fun build() = UseCaseException()
    }
}