package com.luggsoft.pml

/**
 * Represents a node name provider, responsible for mapping node classes to names for use in a query.
 *
 * This implementation uses a [kotlin.collections.Map] of [java.lang.Class] to [kotlin.String] to determine the name of the node.
 *
 * @property classToNameMapping
 */
class MappingNodeNameProvider(
    private val classToNameMapping: Map<Class<*>, String>,
) : NodeNameProvider
{
    constructor(vararg classToNamePairs: Pair<Class<*>, String>) : this(
        classToNameMapping = classToNamePairs.toMap(),
    )

    override fun getNodeName(clazz: Class<*>): String? = this.classToNameMapping[clazz]
}
