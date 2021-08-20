package com.luggsoft.pml.internal

import com.luggsoft.pml.internal.boxing.Box

/**
 * TODO
 *
 */
class DefaultExpressionEvaluator : ExpressionEvaluator
{
    /**
     * TODO
     *
     * @param expression
     * @param scopeBox
     * @param rootBox
     * @return
     */
    override fun evaluate(expression: Expression, scopeBox: Box<*>, rootBox: Box<*>): Box<*> = when (expression)
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
