package com.luggsoft.pml.core

import com.luggsoft.pml.Selector
import com.luggsoft.pml.core.ast.DeepSelectorElement
import com.luggsoft.pml.core.ast.FlatSelectorElement
import com.luggsoft.pml.core.ast.NameElementMatcher
import com.luggsoft.pml.core.ast.WildElementMatcher

class DefaultSelectorFormatter(
    private val expressionFormatter: ExpressionFormatter,
) : SelectorFormatter
{
    override fun formatSelector(selector: Selector): String
    {
        val stringBuilder = StringBuilder()

        selector.selectorElements.forEach { selectorElement ->
            when (selectorElement)
            {
                is FlatSelectorElement ->
                {
                    stringBuilder.append("> ")
                }

                is DeepSelectorElement ->
                {
                    stringBuilder.append("~ ")
                }
            }

            val elementMatcher = selectorElement.elementMatcher

            when (elementMatcher)
            {
                is NameElementMatcher ->
                {
                    stringBuilder.append(elementMatcher.name)
                }

                is WildElementMatcher ->
                {
                    stringBuilder.append("*")
                }
            }

            val expression = elementMatcher.expression

            if (expression != null)
            {
                stringBuilder.append(" { ")
                stringBuilder.append(this.expressionFormatter.formatExpression(expression))
                stringBuilder.append(" }")
            }

            stringBuilder.append(" ")
        }

        return stringBuilder.toString()
    }
}
