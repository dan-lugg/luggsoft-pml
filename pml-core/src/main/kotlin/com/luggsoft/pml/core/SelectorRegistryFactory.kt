package com.luggsoft.pml.core

interface SelectorRegistryFactory
{
    fun <THandler> createSelectorRegistry(handlerClass: Class<THandler>): SelectorRegistry<THandler>
}
