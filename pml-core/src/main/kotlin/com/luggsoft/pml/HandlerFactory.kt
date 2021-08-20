package com.luggsoft.pml

fun interface HandlerFactory<THandler> : (Class<THandler>) -> THandler
{
    override fun invoke(handlerClass: Class<THandler>): THandler
}

