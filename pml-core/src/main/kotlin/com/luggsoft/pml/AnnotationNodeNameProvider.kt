package com.luggsoft.pml

/**
 * Represents a node name provider, responsible for mapping node classes to names for use in a query.
 *
 * This implementation uses an annotation, and accessor function on that annotation, to determine the name of the node.
 *
 * @param TAnnotation
 * @property annotationClass
 * @property accessor
 */
class AnnotationNodeNameProvider<TAnnotation : Annotation>(
    private val annotationClass: Class<TAnnotation>,
    private val accessor: (TAnnotation) -> String?,
) : NodeNameProvider
{
    override fun getNodeName(clazz: Class<*>): String? = clazz
        .getAnnotation(this.annotationClass)
        .let(this.accessor)

    companion object
    {
        fun getDefault(): AnnotationNodeNameProvider<PmlName> = AnnotationNodeNameProvider(
            annotationClass = PmlName::class.java,
            accessor = PmlName::name,
        )
    }
}
