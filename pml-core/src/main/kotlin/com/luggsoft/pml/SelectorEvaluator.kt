package com.luggsoft.pml

/**
 * TODO
 *
 */
interface SelectorEvaluator
{
    /**
     * TODO
     *
     * @param TNode
     * @param selector
     * @param nodes
     * @return
     */
    fun <TNode : Any> evaluate(selector: Selector, nodes: List<TNode>): Boolean
}
