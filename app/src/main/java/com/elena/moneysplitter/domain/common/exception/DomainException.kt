package com.elena.moneysplitter.domain.common.exception

import java.lang.Exception

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 15:11
 */
abstract class DomainException : Exception {

    internal constructor() : super() {}

    internal constructor(message: String) : super(message) {}

    internal constructor(throwable: Throwable) : super(throwable) {}
}