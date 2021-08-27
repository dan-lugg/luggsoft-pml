package com.luggsoft.pml.core

import com.github.h0tk3y.betterParse.parser.ErrorResult

internal class TreeErrorResult(
    val message: String,
) : ErrorResult()
{
    override fun toString(): String = this.message
}
