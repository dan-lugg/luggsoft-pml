package com.luggsoft.pml.internal

import com.luggsoft.pml.internal.boxing.Box

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
    fun evaluate(expression: Expression, scopeBox: Box<*>, rootBox: Box<*>): Box<*>
}

/**
 * TODO
 *
 * @param expression
 * @param rootBox
 * @return
 */
fun ExpressionEvaluator.evaluate(expression: Expression, rootBox: Box<*>): Box<*> = this.evaluate(
    expression = expression,
    scopeBox = rootBox,
    rootBox = rootBox,
)
