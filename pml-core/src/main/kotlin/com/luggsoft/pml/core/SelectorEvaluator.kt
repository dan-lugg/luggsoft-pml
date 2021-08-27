package com.luggsoft.pml.core

import com.luggsoft.pml.Selector

/**
 * Represents a selector evaluator.
 *
 */
interface SelectorEvaluator
{
    /**
     * Evaluates the [selector] against the [nodes], returning true on success, false otherwise.
     *
     * @param TNode
     * @param selector
     * @param nodes
     * @return
     */
    @Throws(SelectorEvaluationException::class)
    fun <TNode : Any> evaluate(selector: Selector, nodes: List<TNode>): Boolean
}
