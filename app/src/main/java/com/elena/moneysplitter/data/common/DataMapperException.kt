package com.elena.moneysplitter.data.common

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 14:51
 */
class DataMapperException : Exception {
    /**
     * {@inheritDoc}
     */
    constructor() : super() {}

    /**
     * {@inheritDoc}
     */
    constructor(message: String) : super(message) {}

    /**
     * {@inheritDoc}
     */
    constructor(cause: Throwable) : super(cause) {}
}
