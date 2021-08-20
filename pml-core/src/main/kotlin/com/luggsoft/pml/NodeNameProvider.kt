package com.luggsoft.pml

/**
 * Represents a node name provider, responsible for mapping node classes to names for use in a query.
 *
 */
fun interface NodeNameProvider
{
    /**
     * Returns the name registered to the coresponding class, or `null` if there is none.
     *
     * @param clazz
     * @return
     */
    fun getNodeName(clazz: Class<*>): String?
}
