package com.luggsoft.pml.core

class SelectorEvaluationException : RuntimeException
{
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}
