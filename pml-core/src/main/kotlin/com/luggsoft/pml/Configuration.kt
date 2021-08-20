package com.luggsoft.pml

import com.luggsoft.pml.core.DefaultExpressionEvaluator
import com.luggsoft.pml.core.DefaultExpressionFormatter
import com.luggsoft.pml.core.DefaultSelectorEvaluator
import com.luggsoft.pml.core.DefaultSelectorFormatter
import com.luggsoft.pml.core.SelectorEvaluator
import com.luggsoft.pml.core.naming.AnnotationNodeNameProvider
import com.luggsoft.pml.core.naming.CompositeNodeNameProvider
import com.luggsoft.pml.core.naming.DefaultNodeNameProvider

interface Configuration
{
    fun createSelectorEvaluator(): SelectorEvaluator

    object Default : Configuration
    {
        private val nodeNameProvider by lazy {
            return@lazy CompositeNodeNameProvider(
                AnnotationNodeNameProvider(
                    annotationClass = PmlName::class.java,
                    accessor = PmlName::name,
                ),
                DefaultNodeNameProvider(),
            )
        }

        private val expressionEvaluator by lazy {
            return@lazy DefaultExpressionEvaluator(
                expressionFormatter = this.expressionFormatter,
            )
        }

        private val expressionFormatter by lazy {
            return@lazy DefaultExpressionFormatter()
        }

        private val selectorFormatter by lazy {
            return@lazy DefaultSelectorFormatter(
                expressionFormatter = this.expressionFormatter,
            )
        }

        override fun createSelectorEvaluator(): SelectorEvaluator = DefaultSelectorEvaluator(
            nodeNameProvider = this.nodeNameProvider,
            expressionEvaluator = this.expressionEvaluator,
            selectorFormatter = this.selectorFormatter,
        )
    }
}
