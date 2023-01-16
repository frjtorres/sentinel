package com.sandbox.sentinel.exception

open class SentinelException : RuntimeException {

    val params: Array<Any>
    constructor(cause: Throwable): super(cause) { this.params = emptyArray() }
    constructor(message: String): super(message) { this.params = emptyArray() }
    constructor(message: String, params: Array<Any> = emptyArray()): super(message) { this.params = params }
}

class EntityNotFoundException(message: String, params: Array<Any> = emptyArray()) : SentinelException(message, params)