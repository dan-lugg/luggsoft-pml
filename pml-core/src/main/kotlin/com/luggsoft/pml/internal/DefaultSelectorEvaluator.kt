package com.luggsoft.pml.internal

import com.luggsoft.pml.NodeNameProvider
import com.luggsoft.pml.Selector
import com.luggsoft.pml.SelectorEvaluator
import com.luggsoft.pml.internal.boxing.toBooleanBox
import com.luggsoft.pml.internal.boxing.toBox
import java.util.ArrayDeque

/**
 * TODO
 *
 * @property nodeNameProvider
 * @property expressionEvaluator
 */
class DefaultSelectorEvaluator(
    private val nodeNameProvider: NodeNameProvider,
    private val expressionEvaluator: ExpressionEvaluator,
) : SelectorEvaluator
{
    /**
     * TODO
     *
     * @param TNode
     * @param selector
     * @param nodes
     * @return
     */
    override fun <TNode : Any> evaluate(selector: Selector, nodes: List<TNode>): Boolean
    {
        return this.evaluate(
            selectorElementDeque = selector.selectorElements.let(::ArrayDeque),
            nodeDeque = nodes.let(::ArrayDeque),
        )
    }

    private fun <TNode : Any> evaluate(selectorElementDeque: java.util.ArrayDeque<SelectorElement>, nodeDeque: java.util.ArrayDeque<TNode>): Boolean
    {
        when (val selectorElement = selectorElementDeque.removeFirst())
        {
            is FlatSelectorElement ->
            {
                val node = nodeDeque.removeFirst()

                return when (val elementMatcher = selectorElement.elementMatcher)
                {
                    is NameElementMatcher -> this.evaluateNameElementMatcher(
                        node = node,
                        elementMatcher = elementMatcher,
                        selectorElementDeque = selectorElementDeque.clone(),
                        nodeDeque = nodeDeque.clone(),
                    )

                    is WildElementMatcher -> this.evaluateWildElementMatcher(
                        node = node,
                        elementMatcher = elementMatcher,
                        selectorElementDeque = selectorElementDeque.clone(),
                        nodeDeque = nodeDeque.clone(),
                    )
                }
            }

            is DeepSelectorElement ->
            {
                while (nodeDeque.isNotEmpty())
                {
                    val node = nodeDeque.removeFirst()

                    when (val elementMatcher = selectorElement.elementMatcher)
                    {
                        is NameElementMatcher ->
                        {
                            if (this.evaluateNameElementMatcher(
                                    node = node,
                                    elementMatcher = elementMatcher,
                                    selectorElementDeque = selectorElementDeque.clone(),
                                    nodeDeque = nodeDeque.clone(),
                                )
                            ) return true
                        }

                        is WildElementMatcher ->
                        {
                            if (this.evaluateWildElementMatcher(
                                    node = node,
                                    elementMatcher = elementMatcher,
                                    selectorElementDeque = selectorElementDeque.clone(),
                                    nodeDeque = nodeDeque.clone(),
                                )
                            ) return true
                        }
                    }
                }

                return false
            }
        }
    }

    private fun <TNode : Any> evaluateWildElementMatcher(node: TNode, elementMatcher: WildElementMatcher, selectorElementDeque: java.util.ArrayDeque<SelectorElement>, nodeDeque: java.util.ArrayDeque<TNode>): Boolean
    {
        val expression = elementMatcher.expression

        if (expression != null)
        {
            if (this.evaluateExpression(
                    node = node,
                    expression = expression,
                )
            ) return this.evaluate(
                selectorElementDeque = selectorElementDeque.clone(),
                nodeDeque = nodeDeque.clone(),
            )
        }

        return this.evaluate(
            selectorElementDeque = selectorElementDeque.clone(),
            nodeDeque = nodeDeque.clone(),
        )
    }

    private fun <TNode : Any> evaluateNameElementMatcher(node: TNode, elementMatcher: NameElementMatcher, selectorElementDeque: java.util.ArrayDeque<SelectorElement>, nodeDeque: java.util.ArrayDeque<TNode>): Boolean
    {
        val nodeName = this.nodeNameProvider.getNodeName(node::class.java)
            ?: TODO("Could not resolve node name for node $node")

        if (!nodeName.equals(elementMatcher.name, ignoreCase = true)) return false

        val expression = elementMatcher.expression

        if (expression != null)
        {
            if (this.evaluateExpression(
                    node = node,
                    expression = expression,
                )
            ) return this.evaluate(
                selectorElementDeque = selectorElementDeque.clone(),
                nodeDeque = nodeDeque.clone(),
            )

        }

        return true
    }

    private fun <TNode : Any> evaluateExpression(node: TNode, expression: Expression): Boolean
    {
        val nodeBox = node.toBox()
        return this.expressionEvaluator
            .evaluate(
                expression = expression,
                rootBox = nodeBox,
            )
            .toBooleanBox()
            .value
    }
}
