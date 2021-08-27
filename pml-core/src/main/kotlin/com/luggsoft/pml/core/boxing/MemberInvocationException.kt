package com.luggsoft.pml.core.boxing

class MemberInvocationException : RuntimeException
{
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}

