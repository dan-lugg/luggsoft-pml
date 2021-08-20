package com.luggsoft.pml.core

import com.luggsoft.pml.Selector

data class DefaultSelectorRegistry<THandler>(
    val mapping: Map<Selector, Class<THandler>>,
) : SelectorRegistryBase<THandler>()
{
    override val selectors: Set<Selector>
        get() = this.mapping.keys

    override fun getHandlerClassBySelector(selector: Selector?): Class<THandler>? = this.mapping[selector]

    override fun iterator(): Iterator<Map.Entry<Selector, Class<THandler>>> = this.mapping.iterator()
}
