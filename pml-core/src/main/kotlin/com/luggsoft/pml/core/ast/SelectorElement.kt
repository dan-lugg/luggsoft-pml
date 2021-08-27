package com.luggsoft.pml.core.ast

/**
 * TODO
 *
 */
sealed class SelectorElement
{
    /**
     *
     */
    abstract val elementMatcher: ElementMatcher

    /**
     *
     */
    internal abstract val specificityValue: Int
}

/**
 * Represents a "flat" selector element. Flat selector elements inspect only children.
 *
 * In a query, the `>` operator is used to denote a flat selector.
 *
 * The following are example queries using the flat selector operator:
 *
 * ```
 * > Node
 * > *
 * ```
 *
 * @property elementMatcher
 */
data class FlatSelectorElement(
    override val elementMatcher: ElementMatcher,
) : SelectorElement()
{
    override val specificityValue: Int = 10
}

/**
 * Represents a "deep" selector element. Deep selector elements inspect all descendents.
 *
 * In a query, a `~` character is used to denote a deep selector.
 *
 * The following are example queries using the deep selector operator:
 *
 * ```
 * ~ Node
 * ~ *
 * ```
 *
 * @property elementMatcher
 */
data class DeepSelectorElement(
    override val elementMatcher: ElementMatcher,
) : SelectorElement()
{
    override val specificityValue: Int = 1
}
