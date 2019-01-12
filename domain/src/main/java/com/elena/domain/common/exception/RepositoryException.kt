package com.elena.domain.common.exception

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 15:11
 */
class RepositoryException : DomainException {

    constructor() : super() {}

    constructor(message: String) : super(message) {}

    constructor(throwable: Throwable) : super(throwable) {}

}
