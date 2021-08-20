package com.luggsoft.pml.core

import com.luggsoft.pml.core.ast.Expression
import com.luggsoft.pml.core.boxing.Box

/**
 * TODO
 *
 */
interface ExpressionEvaluator
{
    /**
     * TODO
     *
     * @param expression
     * @param scopeBox
     * @param rootBox
     * @return
     */
    @Throws(ExpressionEvaluationException::class)
    fun evaluate(expression: Expression, rootBox: Box<*>, scopeBox: Box<*>): Box<*>
}
