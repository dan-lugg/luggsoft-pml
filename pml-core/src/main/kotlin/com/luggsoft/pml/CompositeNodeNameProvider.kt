package com.luggsoft.pml

/**
 * Represents a node name provider, responsible for mapping node classes to names for use in a query.
 *
 * This implementation delegates to other [com.luggsoft.pml.NodeNameProvider] instances.
 *
 * @property nodeNameProviders
 */
class CompositeNodeNameProvider(
    private val nodeNameProviders: List<NodeNameProvider>,
) : NodeNameProvider
{
    constructor(vararg nodeNameProviders: NodeNameProvider) : this(
        nodeNameProviders = nodeNameProviders.toList(),
    )

    override fun getNodeName(clazz: Class<*>): String? = this.nodeNameProviders
        .firstNotNullOrNull { nodeNameProvider -> nodeNameProvider.getNodeName(clazz) }
}
