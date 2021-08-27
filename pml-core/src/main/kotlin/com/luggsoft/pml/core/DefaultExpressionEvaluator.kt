package com.luggsoft.pml.core

import com.luggsoft.pml.core.ast.AccessMemberExpression
import com.luggsoft.pml.core.ast.Expression
import com.luggsoft.pml.core.ast.InvokeMemberExpression
import com.luggsoft.pml.core.ast.RootExpression
import com.luggsoft.pml.core.ast.ScopedExpression
import com.luggsoft.pml.core.ast.ValueExpression
import com.luggsoft.pml.core.boxing.Box

class DefaultExpressionEvaluator(
    private val expressionFormatter: ExpressionFormatter,
) : ExpressionEvaluator
{
    @Throws(ExpressionEvaluationException::class)
    override fun evaluate(expression: Expression, rootBox: Box<*>, scopeBox: Box<*>): Box<*>
    {
        // @formatter:off
        try
        {
            return when (expression)
            {
                is RootExpression -> rootBox

                is ValueExpression -> expression.valueBox

                is ScopedExpression -> this.evaluate(
                    expression = expression.memberExpression,
                    scopeBox = scopeBox,
                    rootBox = this.evaluate(
                        expression.expression,
                        scopeBox = scopeBox,
                        rootBox = rootBox,
                    ),
                )

                is AccessMemberExpression -> rootBox.accessMember(
                    name = expression.name,
                )

                is InvokeMemberExpression -> rootBox.invokeMember(
                    name = expression.name,
                    argumentBoxes = expression.argumentExpressions.map { argumentExpression ->
                        return@map evaluate(
                            expression = argumentExpression,
                            scopeBox = scopeBox,
                            rootBox = rootBox,
                        )
                    },
                )
            }
        }
        catch (exception: Exception)
        {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Failed to evaluate expression: ")
            stringBuilder.append(this.expressionFormatter.formatExpression(expression))
            throw ExpressionEvaluationException(
                message = stringBuilder.toString(),
                cause = exception,
            )
        }
        // @formatter:on
    }
}
