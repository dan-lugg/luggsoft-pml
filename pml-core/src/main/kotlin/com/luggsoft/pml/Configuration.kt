package com.luggsoft.pml

import com.luggsoft.pml.internal.DefaultExpressionEvaluator
import com.luggsoft.pml.internal.DefaultSelectorEvaluator

interface Configuration
{
    fun createSelectorEvaluator(): SelectorEvaluator

    object Default : Configuration
    {
        override fun createSelectorEvaluator(): SelectorEvaluator = DefaultSelectorEvaluator(
            nodeNameProvider = CompositeNodeNameProvider(
                AnnotationNodeNameProvider(
                    annotationClass = PmlName::class.java,
                    accessor = PmlName::name,
                ),
                DefaultNodeNameProvider(),
            ),
            expressionEvaluator = DefaultExpressionEvaluator(),
        )
    }
}
