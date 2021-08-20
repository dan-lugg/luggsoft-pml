package com.luggsoft.pml

/**
 * Represents a node name provider, responsible for mapping node classes to names for use in a query.
 *
 * This implementation uses the simplified class name to determine the name of the node.
 *
 */
class DefaultNodeNameProvider : NodeNameProvider
{
    override fun getNodeName(clazz: Class<*>): String? = clazz.simpleName
}
