package com.luggsoft.pml.core

import com.luggsoft.pml.Selector

abstract class SelectorRegistryBase<THandler> : SelectorRegistry<THandler>
{
    final override fun <TNode : Any> evaluate(nodes: List<TNode>, selectorEvaluator: SelectorEvaluator): Class<THandler>?
    {
        // @formatter:off
        try
        {
            return this.selectors
                .filter      { selector -> selectorEvaluator.evaluate(selector, nodes) }
                .maxByOrNull { selector -> selector.specificity }
                .let         { selector -> this.getHandlerClassBySelector(selector) }
        }
        catch (exception: Exception)
        {
            TODO("Unhandled exception: $exception")
        }
        // @formatter:on
    }

    protected abstract val selectors: Set<Selector>

    protected abstract fun getHandlerClassBySelector(selector: Selector?): Class<THandler>?
}
