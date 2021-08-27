package com.luggsoft.pml.core

import org.reflections.Reflections

class DefaultSelectorRegistryFactory<TAnnotation : Annotation>(
    private val basePackage: String,
    private val annotationClass: Class<TAnnotation>,
    private val annotationTransformer: (TAnnotation) -> String,
    private val selectorParser: SelectorParser,
) : SelectorRegistryFactory
{
    @Suppress("UNCHECKED_CAST")
    @Throws(SelectorRegistrationException::class)
    override fun <THandler> createSelectorRegistry(handlerClass: Class<THandler>): SelectorRegistry<THandler>
    {
        // @formatter:off
        try
        {
            return Reflections(this.basePackage)
                .getTypesAnnotatedWith(this.annotationClass)
                .filter      { clazz -> handlerClass.isAssignableFrom(clazz) }
                .associateBy { clazz -> clazz.getAnnotation(this.annotationClass) }
                .mapKeys     { (annotation, _) -> this.annotationTransformer(annotation) }
                .mapKeys     { (query, _) -> this.selectorParser.parse(query) }
                .mapValues   { (_, clazz) -> clazz as Class<THandler> }
                .let         { mapping -> DefaultSelectorRegistry(mapping) }
        }
        catch (exception: Exception)
        {
            throw SelectorRegistrationException(
                message = "Failed to register selectors and create the registry",
                cause = exception,
            )
        }
        // @formatter:on
    }
}

