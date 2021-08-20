package com.luggsoft.pml

import com.luggsoft.pml.internal.DeepSelectorElement
import com.luggsoft.pml.internal.DefaultExpressionEvaluator
import com.luggsoft.pml.internal.DefaultSelector
import com.luggsoft.pml.internal.DefaultSelectorEvaluator
import com.luggsoft.pml.internal.FlatSelectorElement
import com.luggsoft.pml.internal.NameElementMatcher
import com.luggsoft.pml.internal.RootExpression
import com.luggsoft.pml.internal.WildElementMatcher

interface Node

class ANode : Node

class BNode : Node

class CNode : Node

fun main()
{
    val selectorEvaluator = DefaultSelectorEvaluator(
        /*
        nodeNameProvider = MappingNodeNameProvider(
            ANode::class.java to "ANode",
            BNode::class.java to "BNode",
            CNode::class.java to "CNode",
        ),
        nodeNameProvider = SimpleClassNodeNameProvider(),
        */
        nodeNameProvider = NodeNameProvider { clazz ->
            return@NodeNameProvider clazz.simpleName
        },
        expressionEvaluator = DefaultExpressionEvaluator(),
    )

    val selector = DefaultSelector(
        selectorElements = listOf(
            FlatSelectorElement(
                elementMatcher = NameElementMatcher(name = "ANode")
            ),
            DeepSelectorElement(
                elementMatcher = WildElementMatcher(
                    expression = RootExpression,
                ),
            ),
        )
    )

    try
    {
        1
    } catch (exception: Exception)
    {
        2
    }

    println(selector.specificity)
}
