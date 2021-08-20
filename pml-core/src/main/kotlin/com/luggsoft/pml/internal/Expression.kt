package com.luggsoft.pml.internal

import com.luggsoft.pml.internal.boxing.BooleanBox
import com.luggsoft.pml.internal.boxing.Box
import com.luggsoft.pml.internal.boxing.NullBox

/**
 * TODO
 *
 */
sealed class Expression
{
    /**
     *
     */
    internal abstract val specificityValue: Int
}

/**
 *
 */
object RootExpression : Expression()
{
    /**
     *
     */
    override val specificityValue: Int = 1
}

/**
 * TODO
 *
 * @property valueBox
 */
open class ValueExpression(
    val valueBox: Box<*>,
) : Expression()
{
    /**
     *
     */
    override val specificityValue: Int = 1

    object Null : ValueExpression(
        valueBox = NullBox,
    )

    object True : ValueExpression(
        valueBox = BooleanBox.True,
    )

    object False : ValueExpression(
        valueBox = BooleanBox.False,
    )
}

/**
 * TODO
 *
 * @property expression
 * @property memberExpression
 */
data class ScopedExpression(
    val expression: Expression,
    val memberExpression: MemberExpression,
) : Expression()
{
    /**
     *
     */
    override val specificityValue: Int = 1 + this.expression.specificityValue + this.memberExpression.specificityValue
}

/**
 * TODO
 *
 */
sealed class MemberExpression : Expression()
{
    /**
     *
     */
    abstract val name: String
}

/**
 * TODO
 *
 * @property name
 */
data class AccessMemberExpression(
    override val name: String,
) : MemberExpression()
{
    /**
     *
     */
    override val specificityValue: Int = 1
}

/**
 * TODO
 *
 * @property name
 * @property argumentExpressions
 */
data class InvokeMemberExpression(
    override val name: String,
    val argumentExpressions: List<Expression>,
) : MemberExpression()
{
    /**
     *
     */
    override val specificityValue: Int
        get() = 1 + this.argumentExpressions.sumOf(Expression::specificityValue)
}

