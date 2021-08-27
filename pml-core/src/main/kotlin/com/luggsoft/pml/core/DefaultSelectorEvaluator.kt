package com.luggsoft.pml.core

import com.luggsoft.pml.Selector
import com.luggsoft.pml.core.ast.DeepSelectorElement
import com.luggsoft.pml.core.ast.Expression
import com.luggsoft.pml.core.ast.FlatSelectorElement
import com.luggsoft.pml.core.ast.NameElementMatcher
import com.luggsoft.pml.core.ast.SelectorElement
import com.luggsoft.pml.core.ast.WildElementMatcher
import com.luggsoft.pml.core.boxing.toBooleanBox
import com.luggsoft.pml.core.boxing.toBox
import com.luggsoft.pml.core.naming.NodeNameProvider
import com.luggsoft.pml.core.naming.UnresolvedNodeNameException
import java.util.ArrayDeque

class DefaultSelectorEvaluator(
    private val nodeNameProvider: NodeNameProvider,
    private val expressionEvaluator: ExpressionEvaluator,
    private val selectorFormatter: SelectorFormatter,
) : SelectorEvaluator
{
    override fun <TNode : Any> evaluate(selector: Selector, nodes: List<TNode>): Boolean
    {
        // @formatter:off
        try
        {
            return this.evaluate(
                selectorElementDeque = selector.selectorElements.let(::ArrayDeque),
                nodeDeque = nodes.let(::ArrayDeque),
            )
        }
        catch (exception: Exception)
        {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Failed to evaluate the selector: ")
            stringBuilder.append(this.selectorFormatter.formatSelector(selector))
            throw SelectorEvaluationException(
                message = stringBuilder.toString(),
                cause = exception,
            )
        }
        // @formatter:on
    }

    internal fun <TNode : Any> evaluate(selectorElementDeque: ArrayDeque<SelectorElement>, nodeDeque: ArrayDeque<TNode>): Boolean
    {
        if (selectorElementDeque.isEmpty())
        {
            return nodeDeque.isEmpty()
        }

        when (val selectorElement = selectorElementDeque.removeFirst())
        {
            is FlatSelectorElement ->
            {
                if (nodeDeque.isEmpty())
                {
                    return false
                }

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
                when (val elementMatcher = selectorElement.elementMatcher)
                {
                    is NameElementMatcher ->
                    {
                        while (nodeDeque.isNotEmpty())
                        {
                            val node = nodeDeque.removeFirst()

                            if (this.evaluateNameElementMatcher(
                                    node = node,
                                    elementMatcher = elementMatcher,
                                    selectorElementDeque = selectorElementDeque.clone(),
                                    nodeDeque = nodeDeque.clone(),
                                )
                            )
                            {
                                return true
                            }
                        }

                        return false
                    }

                    is WildElementMatcher ->
                    {
                        while (nodeDeque.isNotEmpty())
                        {
                            val node = nodeDeque.removeFirst()

                            if (this.evaluateWildElementMatcher(
                                    node = node,
                                    elementMatcher = elementMatcher,
                                    selectorElementDeque = selectorElementDeque.clone(),
                                    nodeDeque = nodeDeque.clone(),
                                )
                            )
                            {
                                return true
                            }
                        }

                        return false
                    }
                }
            }
        }
    }

    internal fun <TNode : Any> evaluateWildElementMatcher(node: TNode, elementMatcher: WildElementMatcher, selectorElementDeque: ArrayDeque<SelectorElement>, nodeDeque: ArrayDeque<TNode>): Boolean
    {
        val expression = elementMatcher.expression

        if (expression != null)
        {
            if (this.evaluateExpression(
                    node = node,
                    expression = expression,
                )
            )
            {
                return this.evaluate(
                    selectorElementDeque = selectorElementDeque.clone(),
                    nodeDeque = nodeDeque.clone(),
                )
            }

            return false
        }

        return this.evaluate(
            selectorElementDeque = selectorElementDeque.clone(),
            nodeDeque = nodeDeque.clone(),
        )
    }

    internal fun <TNode : Any> evaluateNameElementMatcher(node: TNode, elementMatcher: NameElementMatcher, selectorElementDeque: ArrayDeque<SelectorElement>, nodeDeque: ArrayDeque<TNode>): Boolean
    {
        val nodeName = this.nodeNameProvider.getNodeName(node::class.java)
            ?: throw UnresolvedNodeNameException("Could not resolve node name for node ${node::class}")

        if (!nodeName.equals(elementMatcher.name, ignoreCase = true))
        {
            return false
        }

        val expression = elementMatcher.expression

        if (expression != null)
        {
            if (this.evaluateExpression(
                    node = node,
                    expression = expression,
                )
            )
            {
                return this.evaluate(
                    selectorElementDeque = selectorElementDeque.clone(),
                    nodeDeque = nodeDeque.clone(),
                )
            }

            return false
        }

        return this.evaluate(
            selectorElementDeque = selectorElementDeque.clone(),
            nodeDeque = nodeDeque.clone(),
        )
    }

    internal fun <TNode : Any> evaluateExpression(node: TNode, expression: Expression): Boolean
    {
        val nodeBox = node.toBox()
        return this.expressionEvaluator
            .evaluate(
                expression = expression,
                scopeBox = nodeBox,
                rootBox = nodeBox,
            )
            .toBooleanBox()
            .value
    }
}

