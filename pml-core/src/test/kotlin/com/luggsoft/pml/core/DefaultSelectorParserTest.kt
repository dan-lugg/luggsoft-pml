package com.luggsoft.pml.core

import com.luggsoft.pml.core.ast.AccessMemberExpression
import com.luggsoft.pml.core.ast.DeepSelectorElement
import com.luggsoft.pml.core.ast.FlatSelectorElement
import com.luggsoft.pml.core.ast.InvokeMemberExpression
import com.luggsoft.pml.core.ast.NameElementMatcher
import com.luggsoft.pml.core.ast.Operator
import com.luggsoft.pml.core.ast.RootExpression
import com.luggsoft.pml.core.ast.ScopedExpression
import com.luggsoft.pml.core.ast.ValueExpression
import com.luggsoft.pml.core.boxing.IntBox
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DefaultSelectorParserTest
{
    private val selectorParser by lazy { DefaultSelectorParser() }

    private val queryToSelectorMapping = mapOf(
        "> A" to DefaultSelector(
            selectorElements = listOf(
                FlatSelectorElement(
                    elementMatcher = NameElementMatcher(
                        name = "A",
                    ),
                ),
            ),
        ),
        "~ A" to DefaultSelector(
            selectorElements = listOf(
                DeepSelectorElement(
                    elementMatcher = NameElementMatcher(
                        name = "A",
                    ),
                ),
            ),
        ),
        "> A ~ B" to DefaultSelector(
            selectorElements = listOf(
                FlatSelectorElement(
                    elementMatcher = NameElementMatcher(
                        name = "A",
                    ),
                ),
                DeepSelectorElement(
                    elementMatcher = NameElementMatcher(
                        name = "B",
                    ),
                ),
            ),
        ),
        "> A { true }" to DefaultSelector(
            selectorElements = listOf(
                FlatSelectorElement(
                    elementMatcher = NameElementMatcher(
                        name = "A",
                        expression = ValueExpression.True,
                    ),
                ),
            ),
        ),
        "> A { $.x == 123i }" to DefaultSelector(
            selectorElements = listOf(
                FlatSelectorElement(
                    elementMatcher = NameElementMatcher(
                        name = "A",
                        expression = InvokeMemberExpression(
                            name = Operator.COMPARE_ABSOLUTE_EQ.name,
                            argumentExpressions = listOf(
                                ScopedExpression(
                                    expression = RootExpression,
                                    memberExpression = AccessMemberExpression(
                                        name = "x"
                                    ),
                                ),
                                ValueExpression(
                                    valueBox = IntBox(
                                        value = 123,
                                    ),
                                ),
                            ),
                        ),
                    ),
                ),
            ),
        ),
    )

    @Test
    fun testParse()
    {
        for ((query, expectedSelector) in this.queryToSelectorMapping)
        {
            val actualSelector = this.selectorParser.parse(query)

            Assertions.assertEquals(expectedSelector, actualSelector)
        }
    }
}
