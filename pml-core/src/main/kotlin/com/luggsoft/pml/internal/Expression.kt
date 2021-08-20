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

    /**
     * TODO
     *
     * @param other
     * @return
     */
    abstract override fun equals(other: Any?): Boolean

    /**
     * TODO
     *
     * @return
     */
    abstract override fun hashCode(): Int

    /**
     * TODO
     *
     * @return
     */
    abstract override fun toString(): String
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

    /**
     * TODO
     *
     * @param other
     * @return
     */
    override fun equals(other: Any?): Boolean = when (other)
    {
        is RootExpression -> true
        else -> false
    }

    /**
     * TODO
     *
     * @return
     */
    override fun hashCode(): Int = 13

    /**
     * TODO
     *
     * @return
     */
    override fun toString(): String = "$"
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

    /**
     * TODO
     *
     * @param other
     * @return
     */
    override fun equals(other: Any?): Boolean = when (other)
    {
        is ValueExpression -> this.valueBox == other.valueBox
        else -> false
    }

    /**
     * TODO
     *
     * @return
     */
    override fun hashCode(): Int = 13 * (this.valueBox.hashCode() + 1)

    /**
     * TODO
     *
     * @return
     */
    override fun toString(): String = when (this.valueBox.value)
    {
        is Int -> "${this.valueBox}i"
        is Long -> "${this.valueBox}l"
        is Float -> "${this.valueBox}f"
        is Double -> "${this.valueBox}d"
        is String -> "'${this.valueBox}'"
        else -> this.valueBox.toString()
    }

    /**
     *
     */
    object Null : ValueExpression(
        valueBox = NullBox,
    )

    /**
     *
     */
    object True : ValueExpression(
        valueBox = BooleanBox.True,
    )

    /**
     *
     */
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

    /**
     * TODO
     *
     * @param other
     * @return
     */
    override fun equals(other: Any?): Boolean = when (other)
    {
        is ScopedExpression -> this.expression == other.expression && this.memberExpression == other.memberExpression
        else -> false
    }

    /**
     * TODO
     *
     * @return
     */
    override fun hashCode(): Int = 13 * (1 + this.expression.hashCode() + this.memberExpression.hashCode())
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

    /**
     * TODO
     *
     * @param other
     * @return
     */
    override fun equals(other: Any?): Boolean = when (other)
    {
        is AccessMemberExpression -> this.name == other.name
        else -> false
    }

    /**
     * TODO
     *
     * @return
     */
    override fun hashCode(): Int = 13 * (1 + this.name.hashCode())
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

    /**
     * TODO
     *
     * @param other
     * @return
     */
    override fun equals(other: Any?): Boolean = when (other)
    {
        is InvokeMemberExpression -> this.name == other.name && this.argumentExpressions == other.argumentExpressions
        else -> false
    }

    /**
     * TODO
     *
     * @return
     */
    override fun hashCode(): Int = (13 * (1 + this.name.hashCode())) + (this.argumentExpressions.sumOf(Expression::hashCode) + 1)
}

