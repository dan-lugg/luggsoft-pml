package com.luggsoft.pml.core

import com.luggsoft.pml.Configuration
import com.luggsoft.pml.Selector
import com.luggsoft.pml.core.ast.SelectorElement

data class DefaultSelector(
    override val selectorElements: List<SelectorElement>,
) : Selector
{
    override val specificity by lazy { Specificity(this) }

    override fun <TNode : Any> evaluate(nodes: List<TNode>): Boolean = this.evaluate(
        nodes = nodes,
        configuration = Configuration.Default,
    )

    override fun <TNode : Any> evaluate(nodes: List<TNode>, configuration: Configuration): Boolean = configuration
        .createSelectorEvaluator()
        .evaluate(
            selector = this,
            nodes = nodes,
        )
}
