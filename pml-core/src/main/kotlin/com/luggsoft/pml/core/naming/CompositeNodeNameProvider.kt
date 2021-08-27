package com.luggsoft.pml.core.naming

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

    override fun getNodeName(clazz: Class<*>): String?
    {
        for (nodeNameProvider in this.nodeNameProviders)
        {
            return nodeNameProvider.getNodeName(clazz)
                ?: continue
        }

        return null
    }
}
