package com.luggsoft.pml

import com.luggsoft.pml.core.DefaultSelectorParser
import com.luggsoft.pml.core.DefaultSelectorRegistryFactory
import com.luggsoft.pml.core.SelectorEvaluator
import com.luggsoft.pml.core.SelectorRegistryFactory

class DefaultSelectorEngine<THandler>(
    private val handlerClass: Class<THandler>,
    private val handlerFactory: HandlerFactory<THandler>,
    private val selectorEvaluator: SelectorEvaluator,
    private val selectorRegistryFactory: SelectorRegistryFactory,
) : SelectorEngine<THandler>
{
    private val selectorRegistry by lazy {
        return@lazy this.selectorRegistryFactory.createSelectorRegistry(this.handlerClass)
    }

    override fun <TNode : Any> evaluate(nodes: List<TNode>): THandler?
    {
        val handlerClass = this.selectorRegistry.evaluate(nodes, this.selectorEvaluator)
            ?: return null

        return this.handlerFactory.invoke(handlerClass)
    }

    companion object
    {
        fun <THandler> createDefault(
            basePackage: String,
            handlerClass: Class<THandler>,
            handlerFactory: HandlerFactory<THandler>,
        ): SelectorEngine<THandler> = DefaultSelectorEngine(
            handlerClass = handlerClass,
            handlerFactory = handlerFactory,
            selectorEvaluator = Configuration.Default.createSelectorEvaluator(),
            selectorRegistryFactory = DefaultSelectorRegistryFactory(
                basePackage = basePackage,
                annotationClass = PmlQuery::class.java,
                annotationTransformer = PmlQuery::query,
                selectorParser = DefaultSelectorParser(),
            ),
        )

        inline fun <reified THandler> createDefault(
            basePackage: String,
            handlerFactory: HandlerFactory<THandler>,
        ): SelectorEngine<THandler> = this.createDefault(
            basePackage = basePackage,
            handlerClass = THandler::class.java,
            handlerFactory = handlerFactory,
        )
    }
}
