package com.luggsoft.pml.internal

/**
 * TODO
 *
 */
sealed class ElementMatcher
{
    abstract val expression: Expression?

    internal abstract val specificityValue: Int
}

/**
 * Represents a named matcher. Named matchers only match if the node names are case-insensitively equal.
 *
 * In a query, a named identifier is used to denote a named matcher.
 *
 * The following are examples using named matchers:
 *
 * ```
 * > Node
 * ~ Node
 * ```
 *
 * @property name
 * @property expression
 */
data class NameElementMatcher(
    val name: String,
    override val expression: Expression? = null,
) : ElementMatcher()
{
    override val specificityValue: Int = 10
}

/**
 * Represents a wildcard matcher. Wildcard matchers match all nodes, regardless of name.
 *
 * In a query, a `*` character is used to denote a wildcard matcher.
 *
 * The following are examples using wildcard matchers:
 *
 * ```
 * > *
 * ~ *
 * ```
 *
 * @property expression
 */
data class WildElementMatcher(
    override val expression: Expression? = null,
) : ElementMatcher()
{
    override val specificityValue: Int = 1
}
