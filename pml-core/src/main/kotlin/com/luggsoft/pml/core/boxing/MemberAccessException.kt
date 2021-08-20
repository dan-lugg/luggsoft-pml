package com.luggsoft.pml.core.boxing

class MemberAccessException : RuntimeException
{
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}
