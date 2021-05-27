package com.elena.data.common

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 14:51
 */
class DataMapperException : Exception {

    constructor() : super()

    constructor(message: String) : super(message)

    constructor(cause: Throwable) : super(cause)
}
