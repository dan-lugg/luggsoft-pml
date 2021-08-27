package com.luggsoft.pml

import com.luggsoft.pml.core.Specificity
import com.luggsoft.pml.core.ast.SelectorElement

/**
 * Represents a query selector.
 *
 */
interface Selector
{
    /**
     * The specificity of this selector.
     *
     * @see com.luggsoft.pml.core.Specificity
     */
    val specificity: Specificity

    /**
     * The selector elements comprising this selector.
     *
     */
    val selectorElements: List<SelectorElement>

    /**
     * Determines if this selector matches the provided nodes.
     *
     * @param TNode
     * @param nodes
     * @return
     */
    fun <TNode : Any> evaluate(nodes: List<TNode>): Boolean

    /**
     * Determines if this selector matches the provided nodes, using the provided configuration.
     *
     * @param TNode
     * @param nodes
     * @param configuration
     * @return
     */
    fun <TNode : Any> evaluate(nodes: List<TNode>, configuration: Configuration): Boolean
}
