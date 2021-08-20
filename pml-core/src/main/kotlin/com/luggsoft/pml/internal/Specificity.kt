package com.luggsoft.pml.internal

import com.luggsoft.pml.Selector

/**
 * TODO
 *
 * @property value1
 * @property value2
 * @property value3
 */
data class Specificity(
    val value1: Int,
    val value2: Int,
    val value3: Int,
) : Comparable<Specificity>
{
    override fun compareTo(other: Specificity): Int
    {
        if (this.value1 == other.value1)
        {
            if (this.value2 == other.value2)
            {
                if (this.value3 == other.value3)
                {
                    return 0
                }

                return this.value3.compareTo(other.value3)
            }

            return this.value2.compareTo(other.value2)
        }

        return this.value1.compareTo(other.value1)
    }

    companion object
    {
        @JvmStatic
        fun createFromSelector(selector: Selector): Specificity = Specificity(
            value1 = getSpecificityValue1(selector),
            value2 = getSpecificityValue2(selector),
            value3 = getSpecificityValue3(selector),
        )

        @JvmStatic
        internal fun getSpecificityValue1(selector: Selector): Int = selector.selectorElements
            .sumBy(SelectorElement::specificityValue)

        @JvmStatic
        internal fun getSpecificityValue2(selector: Selector) = selector.selectorElements
            .map(SelectorElement::elementMatcher)
            .sumBy(ElementMatcher::specificityValue)

        @JvmStatic
        internal fun getSpecificityValue3(selector: Selector) = selector.selectorElements
            .map(SelectorElement::elementMatcher)
            .mapNotNull(ElementMatcher::expression)
            .sumBy(Expression::specificityValue)

    }
}
