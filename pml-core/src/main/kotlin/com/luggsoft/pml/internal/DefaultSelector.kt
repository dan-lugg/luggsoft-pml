package com.luggsoft.pml.internal

import com.luggsoft.pml.Configuration
import com.luggsoft.pml.Selector

data class DefaultSelector(
    override val selectorElements: List<SelectorElement>,
) : Selector
{
    override val specificity by lazy { Specificity.createFromSelector(this) }

    override fun <TNode : Any> evaluate(nodes: List<TNode>): Boolean = this.evaluate(
        nodes = nodes,
        configuration = Configuration.Default,
    )

    override fun <TNode : Any> evaluate(nodes: List<TNode>, configuration: Configuration): Boolean
    {
        val selectorEvaluator = configuration.createSelectorEvaluator()
        return selectorEvaluator.evaluate(
            selector = this,
            nodes = nodes,
        )
    }
}
